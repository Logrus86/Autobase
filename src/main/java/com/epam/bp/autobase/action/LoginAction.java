package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.DaoManager;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.util.AttributeSetter;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoginAction.class);
    private static final ActionResult LOGIN_ADMIN = new ActionResult(ActionFactory.PAGE_ADMIN_ORDERS, true);
    private static final ActionResult LOGIN_DRIVER = new ActionResult(ActionFactory.PAGE_MAIN_DRIVER, true);
    private static final ActionResult LOGIN_FALSE = new ActionResult(ActionFactory.PAGE_MAIN);
    private static final String RB_NAME = "i18n.text";
    private static final String ATTR_LOCALE = "locale";
    private static final String ERROR = "errormsg";
    private static final String ERROR_LOGIN = "error.login";
    private static final String REFERER = "Referer";
    private static final String ORDER = "order";
    private static final String DRIVER_VEHICLES = "driverVehicles";
    private static final String USER = "user";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static String login_err_msg;
    private ActionResult result;
    @Inject
    AttributeSetter as;

    public LoginAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(ATTR_LOCALE);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        login_err_msg = RB.getString(ERROR_LOGIN);

        RegisterAction.clearRegData(session);
        //validate inputs
        String error = Validator.validateRequestParameters(request);
        if (!error.isEmpty()) {
            session.setAttribute(ERROR, error);
            return LOGIN_FALSE;
        }
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                String username = request.getParameter(USERNAME);
                String password = request.getParameter(PASSWORD);
                UserDao userDao = daoManager1.getUserDao();
                User user = userDao.getByCredentials(username, password);
                // user was not found:
                if (user == null) {
                    LOGGER.info("User '" + username + "' with password '" + password + "' wasn't found.");
                    session.setAttribute(ERROR, login_err_msg);
                    result = LOGIN_FALSE;
                    // user was found, all is ok:
                } else {
                    LOGGER.info("User '" + user.getUsername() + "' have logged-in");
                    session.setAttribute(USER, user);
                    session.setAttribute(ERROR, "");
                    //check user roles
                    if (user.getRole() == User.Role.ADMIN) {
                        as.setToSession(ORDER, session);
                        as.setToSession(USER, session);
                        result = LOGIN_ADMIN;
                    }
                    else if (user.getRole() == User.Role.CLIENT) {
                        String referer = request.getHeader(REFERER);
                        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
                        result = new ActionResult(referer, true);
                    } else if (user.getRole() == User.Role.DRIVER) {
                        as.setToSession(DRIVER_VEHICLES, session);
                        result = LOGIN_DRIVER;
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