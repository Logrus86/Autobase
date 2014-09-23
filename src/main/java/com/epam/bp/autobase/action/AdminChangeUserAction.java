package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.H2DaoManager;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ResourceBundle;

public class AdminChangeUserAction implements Action {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    ActionResult main_admin = new ActionResult("main", true);
    private static final String ERROR_BUSY_USERNAME = ResourceBundle.getBundle("i18n.text").getString("error.busy-username");
    private static final String ERROR = "change_error";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String DOB = "dob";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String BALANCE = "balance";
    private static final String ROLE = "role";
    private static final String SAVE = "save";
    private static final String DELETE = "delete";
    private static final String ENTITY_CHANGES_FLAG = "listsChanged";

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();

        //if parameter save not null, we are updating user
        if (request.getParameter(SAVE) != null) {
            String error = Validator.validateRequestParametersMap(request);
            if (!error.isEmpty()) {
                session.setAttribute(ERROR, error);
                return main_admin;
            }
            try {
                DaoFactory daoFactory = DaoFactory.getInstance();
                H2DaoManager daoManager = daoFactory.getDaoManager();
                daoManager.transactionAndClose(new H2DaoManager.DaoCommand() {
                    @Override
                    public void execute(H2DaoManager daoManager) throws DaoException {
                        Integer id = Integer.parseInt(request.getParameter("save"));
                        String firstname = request.getParameter(FIRSTNAME);
                        String lastname = request.getParameter(LASTNAME);
                        String dob = request.getParameter(DOB);
                        String username = request.getParameter(USERNAME);
                        String password = request.getParameter(PASSWORD);
                        String email = request.getParameter(EMAIL);
                        BigDecimal balance = new BigDecimal(request.getParameter(BALANCE));
                        User.Role role = User.Role.valueOf(request.getParameter(ROLE));
                        UserDao userDao = daoManager.getUserDao();
                        //check username not busy
                        if (userDao.getUsersListByUsername(username).size() > 1) {
                            session.setAttribute(ERROR, ERROR_BUSY_USERNAME);
                        } else {
                            //data was entered correctly, proceed
                            User user = new User();
                            user.setFirstname(firstname);
                            user.setLastname(lastname);
                            user.setDob(dob);
                            user.setUsername(username);
                            user.setPassword(password);
                            user.setEmail(email);
                            user.setBalance(balance);
                            user.setRole(role);
                            user.setId(id);
                            userDao.update(user);
                            session.setAttribute(ERROR, "");
                        }
                    }
                });
                daoFactory.releaseContext();
            } catch (Exception e) {
                LOGGER.error("Error at AdminChangeUser while performing transaction to save user");
                throw new ActionException("Error at AdminChangeUser while performing transaction to save user", e);
            }
            session.setAttribute(ENTITY_CHANGES_FLAG, "true");
        }
        //if parameter delete not null, we are deleting user
        if (request.getParameter(DELETE) != null) {
            try {
                DaoFactory daoFactory = DaoFactory.getInstance();
                H2DaoManager daoManager = daoFactory.getDaoManager();
                daoManager.transactionAndClose(new H2DaoManager.DaoCommand() {
                    @Override
                    public void execute(H2DaoManager daoManager) throws DaoException {
                        Integer id = Integer.valueOf(request.getParameter(DELETE));
                        //find vehicle linked with our user by driver-id field and set its field DRIVERID to null
                        VehicleDao vehicleDao = daoManager.getVehicleDao();
                        Vehicle vehicle = vehicleDao.getByDriverId(id);
                        if (vehicle != null) {
                            vehicle.setDriverId(null);
                            vehicleDao.update(vehicle);
                        }
                        //and we can delete our user now
                        UserDao userDao = daoManager.getUserDao();
                        userDao.delete(id);
                    }
                });
                daoFactory.releaseContext();
            } catch (Exception e) {
                LOGGER.error("Error at AdminChangeUser while performing transaction to delete user");
                throw new ActionException("Error at AdminChangeUser while performing transaction to delete user", e);
            }
            session.setAttribute(ENTITY_CHANGES_FLAG, "true");
        }
        return main_admin;
    }
}
