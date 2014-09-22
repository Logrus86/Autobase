package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

public class ChangeUserAction implements Action {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    ActionResult main_admin = new ActionResult("main", true);
    private static final ResourceBundle RB = ResourceBundle.getBundle("i18n.text");
    private static final String ERROR_BUSY_USERNAME = RB.getString("error.busy-username");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        if (request.getParameter("save") != null) {
            String error = Validator.validateRequestParametersMap(request);
            if (!error.isEmpty()) {
                session.setAttribute("change_error", error);
                return main_admin;
            }
            //check username not busy
            try {
                UserDao userDao = DaoFactory.getInstance().getDaoManager().getUserDao();
                Map<String, String> params = new TreeMap<>();
                params.put("username", request.getParameter("username"));
                List<User> users = userDao.findByParams(params);
                if (users.size() > 1) {
                    session.setAttribute("change_error", ERROR_BUSY_USERNAME);
                }
            } catch (Exception e) {
                LOGGER.error("Error at ChangeUserAction while searching for username at database");
                throw new ActionException("Error at ChangeUserAction while searching for username at database", e);
            }
            //data was entered correctly, proceed
            User user = new User();
            user.setFirstname(request.getParameter("firstname"));
            user.setLastname(request.getParameter("lastname"));
            user.setDob(request.getParameter("dob"));
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setEmail(request.getParameter("email"));
            user.setBalance(BigDecimal.valueOf(Double.parseDouble(request.getParameter("balance"))));
            user.setRole(User.Role.valueOf(request.getParameter("role")));
            user.setId(Integer.parseInt(request.getParameter("save")));

            try {
                UserDao userDao = DaoFactory.getInstance().getDaoManager().getUserDao();
                userDao.update(user);
                session.setAttribute("change_error", "");
            } catch (Exception e) {
                throw new ActionException("Error at UpdateUserAction while updating user '" + user.getUsername() + "'", e);
            }
            return main_admin;
        }

        if (request.getParameter("delete") != null) {
            try {
                UserDao userDao = DaoFactory.getInstance().getDaoManager().getUserDao();
                VehicleDao vehicleDao = DaoFactory.getInstance().getDaoManager().getVehicleDao();
                Map<String, String> map = new HashMap<>();
                map.put("DRIVERID", request.getParameter("delete"));
                //find all vehicles linked with our user by driver-id field and set it to null
                List<Vehicle> vehicles = vehicleDao.findByParams(map);
                if (!vehicles.isEmpty()) {
                    for (Vehicle vehicle : vehicles) {
                        vehicle.setDriverId(null);
                        vehicleDao.update(vehicle);
                    }
                }
                //and we can delete our user now
                userDao.delete(Integer.valueOf(request.getParameter("delete")));
                return main_admin;
            } catch (Exception e) {
                throw new ActionException("Error while ChangeUserAction", e);
            }
        }
        return main_admin;
    }
}
