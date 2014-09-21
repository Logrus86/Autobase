package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class LoginAction implements Action { //TODO remember me, true? generate uid, time of life , set it to cookie and user entity at db
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    private ActionResult loginClient = new ActionResult("main-client");
    private ActionResult loginAdmin = new ActionResult("main-admin");
    private ActionResult loginDriver = new ActionResult("main-driver");
    private ActionResult loginFailed = new ActionResult("main");
    private static final ResourceBundle RB = ResourceBundle.getBundle("i18n.text");
    private static final String ERROR_LOGIN = RB.getString("error.login");

    public LoginAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        //check for data inputs
        HttpSession session = request.getSession();
        String error = Validator.validateRequestParametersMap(request);
        if (!error.isEmpty()) {
            session.setAttribute("errormsg", error);
            return loginFailed;
        }

        //username & password were entered, looking for user
        User user;
        try {
            UserDao userDao = DaoFactory.getInstance().getDaoManager().getUserDao();
            Map<String, String> params = new TreeMap<>();
            params.put("username", request.getParameter("username"));
            params.put("password", request.getParameter("password"));
            List<User> users = userDao.findByParams(params);
            if (users.isEmpty()) { //user was not found
                LOGGER.info("User '" + request.getParameter("username") + "' with password '" + request.getParameter("password") + "' wasn't found.");
                session.setAttribute("errormsg", ERROR_LOGIN);
                return loginFailed;
            }
            //user was found, all is ok
            user = users.get(0);
            LOGGER.info("User '" + user.getUsername() + "' have logged-in");
            session.setAttribute("user", user);
            session.setAttribute("errormsg", "");
            //check user roles
            if (user.getRole() == User.Role.ADMIN) return loginAdmin;
            if (user.getRole() == User.Role.CLIENT) return loginClient;
            if (user.getRole() == User.Role.DRIVER) {
                try {
                    VehicleDao vehicleDao = DaoFactory.getInstance().getDaoManager().getVehicleDao();
                    params = new TreeMap<>();
                    params.put("DRIVERID",user.getId().toString());
                    Vehicle vehicle = vehicleDao.findByParams(params).get(0);
                    session.setAttribute("vehicle",vehicle);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
                return loginDriver;
            }

        } catch (Exception e) {
            LOGGER.error("Error at LoginAction while searching for user");
            throw new ActionException("Error at LoginAction while searching for user", e);
        }
        return null;
    }
}