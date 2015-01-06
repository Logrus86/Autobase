package com.epam.bp.autobase.action;

import com.epam.bp.autobase.entity.Entity;
import com.epam.bp.autobase.entity.User;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LogoutAction.class);
    private static final ActionResult MAIN = new ActionResult(ActionFactory.PAGE_MAIN,true);
    private static final String USER = "user";
    public LogoutAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        if (user == null) return MAIN;
        LOGGER.info("User '"+user.getUsername()+"' have logged-out");
        session.removeAttribute(USER);
        return MAIN;
    }
}
