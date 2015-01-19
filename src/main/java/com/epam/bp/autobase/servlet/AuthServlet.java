package com.epam.bp.autobase.servlet;

import com.epam.bp.autobase.entity.User;
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
    private Logger logger;

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    //get method = log-out or login-check
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = us.getSessionUser();
        RequestDispatcher rd;
        if (req.getServletPath().equals("/do/quit")) {
            if (user.getRole() != null) {
                logger.info("User '" + user.getUsername() + "' have logged-out");
                us.initNewUser();
            }
            rd = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        } else {
            if (user.getRole() == null) {
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
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, us.getLocale());
        String login_err_msg = RB.getString(RB_ERROR_LOGIN_KEY);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = us.findByCredentials(username, password);
        if (user != null) {
            logger.info("User '" + username + "' with password '" + password + "' has logged in with role: " + user.getRole());
            us.setSessionUser(user);
            if (user.getRole().equals(User.Role.CLIENT) & (!req.getServletPath().equals("/do/quit"))) {
                resp.sendRedirect(req.getHeader(REFERRER));
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/main" + user.getRole() + ".jsp");
                rd.forward(req, resp);
            }
        } else {
            logger.info("User '" + username + "' with password '" + password + "' wasn't found.");
            us.initNewUser();
            req.setAttribute(ATTRIBUTE_ERROR, login_err_msg);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
            rd.forward(req, resp);
        }
    }
}
