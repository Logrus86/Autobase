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
import java.util.ResourceBundle;

public class LoginAction implements Action {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    private ActionResult loginClient = new ActionResult("main-client");
    private ActionResult loginAdmin = new ActionResult("main-admin");
    private ActionResult loginDriver = new ActionResult("main-driver");
    private ActionResult loginFailed = new ActionResult("main");
    private ActionResult result;
    private static final String LOGIN_ERR_MSG = ResourceBundle.getBundle("i18n.text").getString("error.login");
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String VEHICLE = "vehicle";
    private static final String LOGIN_ERROR = "errormsg";

    public LoginAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        //validate inputs
        HttpSession session = request.getSession();
        String error = Validator.validateRequestParametersMap(request);
        if (!error.isEmpty()) {
            session.setAttribute(LOGIN_ERROR, error);
            return loginFailed;
        }
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            H2DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(new H2DaoManager.DaoCommand() {
                @Override
                public void execute(H2DaoManager daoManager) throws DaoException {
                    String username = request.getParameter(USERNAME);
                    String password = request.getParameter(PASSWORD);
                    UserDao userDao = daoManager.getUserDao();
                    User user = userDao.getByCredentials(username, password);
                    // user was not found:
                    if (user == null) {
                        LOGGER.info("User '" + username + "' with password '" + password + "' wasn't found.");
                        session.setAttribute(LOGIN_ERROR, LOGIN_ERR_MSG);
                        result = loginFailed;
                    // user was found, all is ok:
                    } else {
                        LOGGER.info("User '" + user.getUsername() + "' have logged-in");
                        session.setAttribute(USER, user);
                        session.setAttribute(LOGIN_ERROR, "");
                        //check user roles
                        if (user.getRole() == User.Role.ADMIN) result = loginAdmin;
                        if (user.getRole() == User.Role.CLIENT) result = loginClient;
                        if (user.getRole() == User.Role.DRIVER) {
                            //if user is driver, we must find his vehicle also:
                            try {
                                VehicleDao vehicleDao = daoManager.getVehicleDao();
                                Vehicle vehicle = vehicleDao.getByDriverId(user.getId());
                                session.setAttribute(VEHICLE, vehicle);
                            } catch (Exception e) {
                                throw new DaoException("Error while get vehicle for logined driver",e);
                            }
                            result = loginDriver;
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