package com.epam.bp.autobase.action;

import com.epam.bp.autobase.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckLoginAction implements Action {
    private ActionResult loginAdmin = new ActionResult("main-admin");
    private ActionResult loginClient = new ActionResult("main-client");
    private ActionResult loginDriver = new ActionResult("main-driver");
    private ActionResult loginFalse = new ActionResult("main");

    @Override
    public ActionResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) return loginFalse;
        User user = (User) session.getAttribute("user");
        if (user.getRole() == User.Role.ADMIN) return loginAdmin;
        if (user.getRole() == User.Role.CLIENT) return loginClient;
        if (user.getRole() == User.Role.DRIVER) return loginDriver;
        return loginFalse;
    }
}
