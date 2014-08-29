package com.epam.bp.autobase.action;

import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    private ActionResult result = new ActionResult("main");

    public LogoutAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        LOGGER.info("User log-out");
        session.setAttribute("user", null);
        return result;
    }
}
