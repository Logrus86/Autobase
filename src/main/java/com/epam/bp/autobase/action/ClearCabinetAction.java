package com.epam.bp.autobase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ClearCabinetAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        session.setAttribute("cab_error","");
        return new ActionResult("cabinet");
    }
}
