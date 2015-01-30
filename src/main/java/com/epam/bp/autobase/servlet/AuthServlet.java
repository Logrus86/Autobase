package com.epam.bp.autobase.servlet;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.service.ServiceException;
import com.epam.bp.autobase.service.UserService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

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

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    //get method = log-out or login-check
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ss.getSessionUser();
        RequestDispatcher rd;
        if ((req.getServletPath().equals("/do/quit")) & (user != null)) {
            //log-out
            logger.info("User '" + user.getUsername() + "' have logged-out");
            ss.setSessionUser(null);
            rd = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        } else {
            //log-in check
            if (user == null) {
                logger.info("User isn't logged in, going to main page");
                rd = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
            } else {
                rd = req.getRequestDispatcher("/WEB-INF/jsp/main" + user.getRole() + ".jsp");
            }
        }
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
            ss.setSessionUser(user);
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
