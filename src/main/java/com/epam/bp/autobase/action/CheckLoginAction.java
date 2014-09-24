package com.epam.bp.autobase.action;

import com.epam.bp.autobase.entity.User;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckLoginAction implements Action {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    private ActionResult loginAdmin = new ActionResult("main-admin");
    private ActionResult loginClient = new ActionResult("main-client");
    private ActionResult loginDriver = new ActionResult("main-driver");
    private ActionResult loginFalse = new ActionResult("main");
    private static final String LOGIN_ERROR = "errormsg";
    private static final String SEARCH_ERROR = "search_error";
    private static final String USER = "user";

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_ERROR, "");
        session.setAttribute(SEARCH_ERROR, "");
        //    session.setAttribute("change_error", "");

        if (session.getAttribute(USER) == null) return loginFalse;
        User user = (User) session.getAttribute(USER);

        if (user.getRole() == User.Role.ADMIN) return loginAdmin;
        if (user.getRole() == User.Role.CLIENT) return loginClient;
        if (user.getRole() == User.Role.DRIVER) return loginDriver;

        return loginFalse;
    }
}
