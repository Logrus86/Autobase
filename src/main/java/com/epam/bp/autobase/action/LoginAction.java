package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2UserDao;
import com.epam.bp.autobase.entity.User;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.TreeMap;

public class LoginAction implements Action {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    private ActionResult loginClient = new ActionResult("main-client");
    private ActionResult loginAdmin = new ActionResult("main-admin");
    private ActionResult loginDriver = new ActionResult("main-driver");
    private ActionResult loginFailed = new ActionResult("main");

    public LoginAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        User user;
        try {
            H2UserDao h2UserDao = DaoFactory.getInstance().getH2UserDao();
            Map<String,String> params = new TreeMap<>();
            params.put("username",request.getParameter("username"));
            params.put("password",request.getParameter("password"));
            user = h2UserDao.findByParameters(params);
        } catch (Exception e) {
            throw new ActionException("Error at LoginAction while searching for user", e.getCause());
        }

        if (user == null) {
            LOGGER.info("User '"+request.getParameter("username")+"' with password '"+request.getParameter("password")+"' wasn't found.");
            return loginFailed;
        }
        LOGGER.info("User '"+user.getUsername()+"' have logged-in");

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        if (user.getRole() == User.Role.ADMIN) return loginAdmin;
        if (user.getRole() == User.Role.CLIENT) return loginClient;
        if (user.getRole() == User.Role.DRIVER) return loginDriver;

        return loginFailed;
    }
}