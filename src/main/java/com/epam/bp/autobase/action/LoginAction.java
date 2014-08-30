package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2UserDao;
import com.epam.bp.autobase.entity.User;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class LoginAction implements Action {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    private ActionResult loginClient = new ActionResult("main-client");
    private ActionResult loginAdmin = new ActionResult("main-admin");
    private ActionResult loginDriver = new ActionResult("main-driver");
    private ActionResult loginFailed = new ActionResult("main");
    private static final ResourceBundle RB = ResourceBundle.getBundle("i18n.text");
    private static final String ERROR_LOGIN = RB.getString("error.login");
    private static final String ERROR_EMPTY_IN = RB.getString("error.empty-input");
    private static final String ERROR_EMPTY_LOGIN = RB.getString("error.empty-login");
    private static final String ERROR_EMPTY_PASS = RB.getString("error.empty-pass");

    public LoginAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        //check for data inputs
        HttpSession session = request.getSession();
        if (request.getParameter("username").equals("") & request.getParameter("password").equals("")) {
            session.setAttribute("errormsg", ERROR_EMPTY_IN);
            return loginFailed;
        }
        if (request.getParameter("username").equals("")) {
            session.setAttribute("errormsg", ERROR_EMPTY_LOGIN);
            return loginFailed;
        }
        if (request.getParameter("password").equals("")) {
            session.setAttribute("errormsg", ERROR_EMPTY_PASS);
            return loginFailed;
        }

        //username & password were entered, looking for user
        User user;
        try {
            H2UserDao h2UserDao = DaoFactory.getInstance().getH2UserDao();
            Map<String, String> params = new TreeMap<>();
            params.put("username", request.getParameter("username"));
            params.put("password", request.getParameter("password"));
            user = h2UserDao.findByParameters(params);
        } catch (Exception e) {
            throw new ActionException("Error at LoginAction while searching for user", e.getCause());
        }

        //user was not found (== null)
        if (user == null) {
            LOGGER.info("User '" + request.getParameter("username") + "' with password '" + request.getParameter("password") + "' wasn't found.");
            session.setAttribute("errormsg", ERROR_LOGIN);
            return loginFailed;
        }

        //user was found, all is ok
        LOGGER.info("User '" + user.getUsername() + "' have logged-in");
        session.setAttribute("user", user);
        session.setAttribute("errormsg", "");

        //check user roles
        if (user.getRole() == User.Role.ADMIN) return loginAdmin;
        if (user.getRole() == User.Role.CLIENT) return loginClient;
        if (user.getRole() == User.Role.DRIVER) return loginDriver;

        return null;
    }
}