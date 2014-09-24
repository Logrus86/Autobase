package com.epam.bp.autobase.action;

import com.epam.bp.autobase.entity.User;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LogoutAction.class);
    private static final ActionResult MAIN = new ActionResult("main");
    private static final String ATTR_USER = "user";

    public LogoutAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        LOGGER.info("User '"+((User) session.getAttribute(ATTR_USER)).getUsername()+"' have logged-out");
        session.removeAttribute(ATTR_USER);
        return MAIN;
    }
}
