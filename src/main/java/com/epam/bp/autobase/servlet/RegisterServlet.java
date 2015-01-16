package com.epam.bp.autobase.servlet;

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

@WebServlet({
        "do/register",
})
public class RegisterServlet extends HttpServlet {
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String EMAIL = "email";
    private static final String DOB = "dob";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ATTRIBUTE_ERROR = "reg_error";
    private static final String PASSWORD_REPEAT = "password-repeat";
    @Inject
    UserService us;
    @Inject
    Logger logger;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter(FIRST_NAME);
        String lastName = req.getParameter(LAST_NAME);
        String dob = req.getParameter(DOB);
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        String password_repeat = req.getParameter(PASSWORD_REPEAT);
        String email = req.getParameter(EMAIL);
        try {
            us.register(firstName, lastName, dob, username, password, password_repeat, email);
            logger.info("Newly registered user: " + us.getSessionUser().toString());
            RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/registered.jsp");
            resultView.forward(req, resp);
        } catch (ServiceException se) {
            logger.info(se.getMessage());
            req.setAttribute(ATTRIBUTE_ERROR, se.getMessage());
            RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
            resultView.forward(req, resp);
        }
    }
}
