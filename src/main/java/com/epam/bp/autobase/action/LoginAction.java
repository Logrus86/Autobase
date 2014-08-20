package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action {

    private ActionResult login = new ActionResult("login");
    private ActionResult welcome = new ActionResult("welcome");

    public LoginAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao = DaoFactory.getUserDao();
        User user = UserDao.findByCredentials(username, password);
       /* if (user == null) {
            return login;
        }*/

        if (!"user".equals(username) || !"111".equals(password)) {
            return login;
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        return welcome;
    }
}