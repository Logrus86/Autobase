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
import java.util.HashMap;

@WebServlet({
        "do/register",
})
public class RegisterServlet extends HttpServlet {
    public static final String ATTRIBUTE_ERROR = "msg";
    public static final String PARAM_FIRSTNAME = "firstname";
    public static final String PARAM_LASTNAME = "lastname";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_DOB = "dob";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_PASSWORD_REPEAT = "password-repeat";
    @Inject
    UserService us;
    @Inject
    Logger logger;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        resultView.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, String> userDataMap = new HashMap<>();
        userDataMap.put(PARAM_FIRSTNAME, req.getParameter(PARAM_FIRSTNAME));
        userDataMap.put(PARAM_LASTNAME, req.getParameter(PARAM_LASTNAME));
        userDataMap.put(PARAM_DOB, req.getParameter(PARAM_DOB));
        userDataMap.put(PARAM_USERNAME, req.getParameter(PARAM_USERNAME));
        userDataMap.put(PARAM_PASSWORD, req.getParameter(PARAM_PASSWORD));
        userDataMap.put(PARAM_PASSWORD_REPEAT, req.getParameter(PARAM_PASSWORD_REPEAT));
        userDataMap.put(PARAM_EMAIL, req.getParameter(PARAM_EMAIL));
        try {
            us.create(userDataMap);
            logger.info("Newly registered user: " + us.getSessionUser().toString());
            RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/registered.jsp");
            resultView.forward(req, resp);
        } catch (ServiceException se) {
            logger.error(se.getMessage());
            RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
            resultView.forward(req, resp);
        }
    }
}
