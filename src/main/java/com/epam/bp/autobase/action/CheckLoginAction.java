package com.epam.bp.autobase.action;

import com.epam.bp.autobase.entity.User;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckLoginAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CheckLoginAction.class);
    private static final ActionResult LOGIN_ADMIN = new ActionResult("main-admin");
    private static final ActionResult LOGIN_CLIENT = new ActionResult("main-client");
    private static final ActionResult LOGIN_DRIVER = new ActionResult("main-driver");
    private static final ActionResult LOGIN_FALSE = new ActionResult("main");
    private static final String LOGIN_ERROR = "errormsg";
    private static final String SEARCH_ERROR = "search_error";
    private static final String USER = "user";

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_ERROR, "");
        session.setAttribute(SEARCH_ERROR, "");
        if (session.getAttribute(USER) == null) {
            LOGGER.info("User not logined, going to main page");
            return LOGIN_FALSE;
        }
        User user = (User) session.getAttribute(USER);
        if (user.getRole() == User.Role.ADMIN) return LOGIN_ADMIN;
        if (user.getRole() == User.Role.CLIENT) return LOGIN_CLIENT;
        if (user.getRole() == User.Role.DRIVER) return LOGIN_DRIVER;
        return LOGIN_FALSE;
    }
}
