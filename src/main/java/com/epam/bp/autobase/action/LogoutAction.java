package com.epam.bp.autobase.action;

import com.epam.bp.autobase.util.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAction extends Logger implements Action {
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
