package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2UserDao;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.util.Parser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;

public class RegisterAction implements Action {

    public RegisterAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) {
        User newUser = new User();
        newUser.setId(4);
        newUser.setFirstname(request.getParameter("firstname"));
        newUser.setLastname(request.getParameter("lastname"));
        newUser.setDob(Parser.StringToDate(request.getParameter("dob")));
        newUser.setUsername(request.getParameter("username"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setRole(User.Role.CLIENT); //drivers (and moders?) are added by admin
        newUser.setBalance(BigDecimal.ZERO);
        //TODO foolproof
        H2UserDao h2UserDao = DaoFactory.getH2UserDao();
        try {
            h2UserDao.add(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", newUser);
        return new ActionResult("registered");
    }
}
