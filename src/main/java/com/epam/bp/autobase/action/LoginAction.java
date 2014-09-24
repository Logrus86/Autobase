package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class LoginAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoginAction.class);
    private static final ActionResult LOGIN_ADMIN = new ActionResult("main-admin");
    private static final ActionResult LOGIN_CLIENT = new ActionResult("main-client");
    private static final ActionResult LOGIN_DRIVER = new ActionResult("main-driver");
    private static final ActionResult LOGIN_FALSE = new ActionResult("main");
    private static final String LOGIN_ERR_MSG = ResourceBundle.getBundle("i18n.text").getString("error.login");
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String VEHICLE = "vehicle";
    private static final String LOGIN_ERROR = "errormsg";
    private ActionResult result;

    public LoginAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        //validate inputs
        HttpSession session = request.getSession();
        String error = Validator.validateRequestParametersMap(request);
        if (!error.isEmpty()) {
            session.setAttribute(LOGIN_ERROR, error);
            return LOGIN_FALSE;
        }
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public void execute(DaoManager daoManager) throws DaoException {
                    String username = request.getParameter(USERNAME);
                    String password = request.getParameter(PASSWORD);
                    UserDao userDao = daoManager.getUserDao();
                    User user = userDao.getByCredentials(username, password);
                    // user was not found:
                    if (user == null) {
                        LOGGER.info("User '" + username + "' with password '" + password + "' wasn't found.");
                        session.setAttribute(LOGIN_ERROR, LOGIN_ERR_MSG);
                        result = LOGIN_FALSE;
                        // user was found, all is ok:
                    } else {
                        LOGGER.info("User '" + user.getUsername() + "' have logged-in");
                        session.setAttribute(USER, user);
                        session.setAttribute(LOGIN_ERROR, "");
                        //check user roles
                        if (user.getRole() == User.Role.ADMIN) result = LOGIN_ADMIN;
                        if (user.getRole() == User.Role.CLIENT) result = LOGIN_CLIENT;
                        if (user.getRole() == User.Role.DRIVER) {
                            //if user is driver, we must find his vehicle also:
                            VehicleDao vehicleDao = daoManager.getVehicleDao();
                            Vehicle vehicle = vehicleDao.getByDriverId(user.getId());
                            session.setAttribute(VEHICLE, vehicle);
                            result = LOGIN_DRIVER;
                        }
                    }
                }
            });
            daoFactory.releaseContext();
        } catch (DaoException e) {
            LOGGER.error("Error at LoginAction while performing transaction");
            throw new ActionException("Error at LoginAction while performing transaction", e);
        }
        return result;
    }
}