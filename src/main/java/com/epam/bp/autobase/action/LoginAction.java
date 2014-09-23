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
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class LoginAction implements Action {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    private ActionResult loginClient = new ActionResult("main-client");
    private ActionResult loginAdmin = new ActionResult("main-admin");
    private ActionResult loginDriver = new ActionResult("main-driver");
    private ActionResult loginFailed = new ActionResult("main");
    private ActionResult result;
    private static final String LOGIN_ERR_MSG = ResourceBundle.getBundle("i18n.text").getString("error.login");
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_DRIVERID = "DRIVERID";
    private static final String ATTR_ERROR = "errormsg";
    private static final String ATTR_USER = "user";
    private static final String ATTR_VEHICLE = "vehicle";

    public LoginAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        //validate inputs
        HttpSession session = request.getSession();
        String error = Validator.validateRequestParametersMap(request);
        if (!error.isEmpty()) {
            session.setAttribute(ATTR_ERROR, error);
            return loginFailed;
        }
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            H2DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(new H2DaoManager.DaoCommand() {
                @Override
                public Object execute(H2DaoManager daoManager) throws DaoException {
                    String username = request.getParameter(PARAM_USERNAME);
                    String password = request.getParameter(PARAM_PASSWORD);
                    Map<String, String> params = new TreeMap<>();
                    params.put(PARAM_USERNAME, username);
                    params.put(PARAM_PASSWORD, password);
                    UserDao userDao = daoManager.getUserDao();
                    List<User> users = userDao.findByParams(params);
                    if (users.isEmpty()) { //user was not found
                        LOGGER.info("User '" + username + "' with password '" + password + "' wasn't found.");
                        session.setAttribute(ATTR_ERROR, LOGIN_ERR_MSG);
                        result = loginFailed;
                    } else {
                        //user was found, all is ok
                        User user = users.get(0);
                        LOGGER.info("User '" + user.getUsername() + "' have logged-in");
                        session.setAttribute(ATTR_USER, user);
                        session.setAttribute(ATTR_ERROR, "");
                        //check user roles
                        if (user.getRole() == User.Role.ADMIN) result = loginAdmin;
                        if (user.getRole() == User.Role.CLIENT) result = loginClient;
                        if (user.getRole() == User.Role.DRIVER) {
                            //if user is driver, we must find his vehicle also:
                            try {
                                VehicleDao vehicleDao = daoManager.getVehicleDao();
                                params = new TreeMap<>();
                                params.put(PARAM_DRIVERID, user.getId().toString());
                                Vehicle vehicle = vehicleDao.findByParams(params).get(0);
                                session.setAttribute(ATTR_VEHICLE, vehicle);
                            } catch (Exception e) {
                                throw new DaoException("Error while ge vehicle for log-ined driver",e);
                            }
                            result = loginDriver;
                        }
                    }
                    return result;
                }
            });
        } catch (DaoException e) {
            LOGGER.error("Error at LoginAction while performing transaction");
            throw new ActionException("Error at LoginAction while performing transaction", e);
        }
        return result;
    }
}