package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2UserDao;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.util.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class LoginAction extends Logger implements Action {
    private ActionResult loginClient = new ActionResult("main-client");
    private ActionResult loginAdmin = new ActionResult("main-admin");
    private ActionResult loginDriver = new ActionResult("main-driver");
    private ActionResult loginFailed = new ActionResult("main");

    public LoginAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) {

      //  session.getAttribute("user");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LOGGER.info("username: "+username+", pass: "+password);
        H2UserDao h2UserDao = DaoFactory.getH2UserDao();
        User user = null;
        try {
            user = h2UserDao.findByCredentials(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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