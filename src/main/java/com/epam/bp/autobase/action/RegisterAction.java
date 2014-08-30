package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2UserDao;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.util.DateParser;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class RegisterAction implements Action {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());

    public RegisterAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        User newUser = new User();
        newUser.setId(4);
        newUser.setFirstname(request.getParameter("firstname"));
        newUser.setLastname(request.getParameter("lastname"));
        newUser.setDob(DateParser.StringToDate(request.getParameter("dob")));
        newUser.setUsername(request.getParameter("username"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setRole(User.Role.CLIENT);
        newUser.setBalance(BigDecimal.ZERO);
        //TODO checks
        H2UserDao h2UserDao = null;
        try {
            h2UserDao = DaoFactory.getInstance().getH2UserDao();
            h2UserDao.create(newUser);
        } catch (Exception e) {
            throw new ActionException("Error at RegisterAction while creating user", e.getCause());
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", newUser);
        LOGGER.info(newUser.toString());
        return new ActionResult("registered");
    }
}
