package com.epam.bp.autobase.jsp.servlet;

import com.epam.bp.autobase.ejb.SessionState;
import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.service.ServiceException;
import com.epam.bp.autobase.service.UserService;
import com.epam.bp.autobase.util.AutobaseCookies;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.UUID;

@WebServlet({
        "do/login", // log-in
        "do/main",  // check for logged-in user on main page
        "do/quit"   // log-out
})
public class AuthServlet extends HttpServlet {
    private static final String RB_NAME = "i18n.text";
    private static final String RB_ERROR_LOGIN_KEY = "error.login";
    private static final String ATTRIBUTE_ERROR = "errormsg";
    private static final String REFERRER = "Referer";
    @Inject
    UserService us;
    @Inject
    SessionState ss;
    @Inject
    private Logger logger;

    //get method = log-out or login-check
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ss.getSessionUser();
        RequestDispatcher rd;
        //log-in check
        if ((req.getServletPath().equals("/do/quit")) & (user != null)) {
            //log-out
            user.setUuid(null);
            try {
                us.update(user);
            } catch (ServiceException e) {
                logger.error("Cannot update user to remove UUID");
            }
            logger.info("User '" + user.getUsername() + "' have logged-out");
            Cookie uuidCookie = new Cookie(AutobaseCookies.NAME_UUID, "");
            uuidCookie.setMaxAge(0);
            resp.addCookie(uuidCookie);
            req.getSession().invalidate();
            user = null;
        } else if (user == null) {
            for (Cookie cookie : req.getCookies())
                if (AutobaseCookies.NAME_UUID.equals(cookie.getName())) {
                    try {
                        user = us.getByUuidString(cookie.getValue());
                        logger.info("Logging-in user by UUID:" + cookie.getValue() + " from cookie");
                        ss.setSessionUser(user);
                    } catch (ServiceException e) {
                        logger.error("Cannot retrieve user by UUID: " + cookie.getValue());
                    }
                } else if ((user != null) && (AutobaseCookies.NAME_LANG.equals(cookie.getName())))
                    ss.setLocale(cookie.getValue());
        }
        if (user == null) {
            logger.info("User isn't logged in, going to main page");
            rd = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        } else rd = req.getRequestDispatcher("/WEB-INF/jsp/main" + user.getRole() + ".jsp");
        rd.forward(req, resp);
    }

    // post method = log-in
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResourceBundle rb = ResourceBundle.getBundle(RB_NAME, ss.getLocale());
        String login_err_msg = rb.getString(RB_ERROR_LOGIN_KEY);
        UserDto userDto = new UserDto()
                .setUsername(req.getParameter("username"))
                .setPassword(req.getParameter("password"));
        User user = null;
        try {
            user = us.findByCredentials(userDto);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e.getCause());
        }
        if (user != null) {
            logger.info("User '" + userDto.getUsername() + "' with password '" + userDto.getPassword() + "' has logged in with role: " + user.getRole());
            user.setUuid(UUID.randomUUID().toString());
            try {
                us.update(user);
            } catch (ServiceException e) {
                logger.error(e.getMessage(), e.getCause());
            }
            ss.setSessionUser(user);

            Cookie uuidCookie = new Cookie(AutobaseCookies.NAME_UUID, String.valueOf(user.getUuid()));
            uuidCookie.setMaxAge(AutobaseCookies.MAX_AGE_UUID);
            resp.addCookie(uuidCookie);
            for (Cookie cookie : req.getCookies())
                if (AutobaseCookies.NAME_LANG.equals(cookie.getName())) ss.setLocale(cookie.getValue());

            if (user.getRole().equals(User.Role.CLIENT) & (!req.getHeader(REFERRER).contains("/do/quit"))) {
                resp.sendRedirect(req.getHeader(REFERRER));
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/main" + user.getRole() + ".jsp");
                rd.forward(req, resp);
            }
        } else {
            logger.info("User '" + userDto.getUsername() + "' with password '" + userDto.getPassword() + "' wasn't found.");
            req.setAttribute(ATTRIBUTE_ERROR, login_err_msg);
            ss.setSessionUser(null);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
            rd.forward(req, resp);
        }
    }
}
