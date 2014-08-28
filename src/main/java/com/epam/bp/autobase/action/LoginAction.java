package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2UserDao;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.util.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.TreeMap;

public class LoginAction extends Logger implements Action {
    private ActionResult loginClient = new ActionResult("main-client");
    private ActionResult loginAdmin = new ActionResult("main-admin");
    private ActionResult loginDriver = new ActionResult("main-driver");
    private ActionResult loginFailed = new ActionResult("main");

    public LoginAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) {

        H2UserDao h2UserDao = DaoFactory.getInstance().getH2UserDao();
        Map<String,String> params = new TreeMap<>();
        params.put("username",request.getParameter("username"));
        params.put("password",request.getParameter("password"));
        User user = h2UserDao.findByParameters(params);

        LOGGER.info(user.toString());

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        if (user.getRole() == User.Role.ADMIN) return loginAdmin;
        if (user.getRole() == User.Role.CLIENT) return loginClient;
        if (user.getRole() == User.Role.DRIVER) return loginDriver;
        LOGGER.info("User not found.");
        return loginFailed;
    }
}