package com.epam.bp.autobase.action;

import com.epam.bp.autobase.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class RegisterAction implements Action {

    public RegisterAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) {
        User newUser = new User();
        newUser.setFirstname(request.getParameter("firstname"));
        newUser.setLastname(request.getParameter("lastname"));
        newUser.setLogin(request.getParameter("username"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setRole(User.Role.valueOf(request.getParameter("role")));
        newUser.setCreditBalance(BigDecimal.ZERO);
        return new ActionResult("register");
    }
}
