package com.epam.bp.autobase.servlet;

import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.service.UserService;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@WebServlet({
        "do/login", // log-in
        "do/main",  // check for logged-in user on main page
        "do/quit"   // log-out
})
public class LoginServlet extends HttpServlet {
    private static final String RB_NAME = "i18n.text";
    private static final String RB_ERROR_LOGIN_KEY = "error.login";
    private static final String ATTRIBUTE_ERROR = "errormsg";
    private static final String ATTRIBUTE_LOCALE = "locale";
    private static final String ATTRIBUTE_USER = "user";
    private static final String REFERRER = "Referer";
    @Inject
    UserService us;
    @Inject
    private Logger logger;

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        RequestDispatcher rd;
        if (req.getServletPath().equals("/do/quit")) {
            if (user != null) {
                logger.info("User '" + user.getUsername() + "' have logged-out");
                session.removeAttribute(ATTRIBUTE_USER);
            }
            rd = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        } else {
            if (user == null) {
                logger.info("User isn't logged in, going to main page");
                rd = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");

            } else {
                rd = req.getRequestDispatcher("/WEB-INF/jsp/main" + user.getRole() + ".jsp");
            }
        }
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Locale locale = (Locale) session.getAttribute(ATTRIBUTE_LOCALE);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        String login_err_msg = RB.getString(RB_ERROR_LOGIN_KEY);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = us.findByCredentials(username, password);
        if (user != null) {
            logger.info("User '" + username + "' with password '" + password + "' has logged in with role: " + user.getRole());
            session.setAttribute(ATTRIBUTE_USER, user);
            session.removeAttribute(ATTRIBUTE_ERROR);
            if (user.getRole().equals(User.Role.CLIENT) & (!req.getServletPath().equals("/do/quit"))) {
                resp.sendRedirect(req.getHeader(REFERRER));
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/main" + user.getRole() + ".jsp");
                rd.forward(req, resp);
            }
        } else {
            logger.info("User '" + username + "' with password '" + password + "' wasn't found.");
            session.removeAttribute(ATTRIBUTE_USER);
            session.setAttribute(ATTRIBUTE_ERROR, login_err_msg);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
            rd.forward(req, resp);
        }
    }
}
