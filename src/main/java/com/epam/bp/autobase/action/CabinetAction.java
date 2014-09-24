package com.epam.bp.autobase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CabinetAction implements Action {
    private static final String ERROR = "user_change_error";
    private static final ActionResult CABINET = new ActionResult("cabinet");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        session.setAttribute(ERROR,"");
        return CABINET;
    }
}
