package com.epam.bp.autobase.action;

import com.epam.bp.autobase.entity.User;
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
        LOGGER.info("User '"+((User) session.getAttribute("user")).getUsername()+"' have logged-out");
        session.setAttribute("user", null);
        return result;
    }
}
