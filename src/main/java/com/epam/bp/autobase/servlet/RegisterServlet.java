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
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet({
        "do/register",
})
public class RegisterServlet extends HttpServlet {
    @Inject
    UserService us;
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String EMAIL = "email";
    private static final String DOB = "dob";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ATTRIBUTE_ERROR = "reg_error";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder errorMessage = new StringBuilder();
        try {
            String firstname = req.getParameter(FIRST_NAME);
            String lastname = req.getParameter(LAST_NAME);
            String dob = req.getParameter(DOB);
            String username = req.getParameter(USERNAME);
            String password = req.getParameter(PASSWORD);
            String email = req.getParameter(EMAIL);
            User user;
            while ((user = us.getNewUser()) == null) {
                us.initNewUser();
            }
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setDob(dob);
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setRole(User.Role.CLIENT);
            user.setBalance(BigDecimal.ZERO);
            us.register();

            RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/registered.jsp");
            resultView.forward(req, resp);
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
                errorMessage.append(t.getMessage());
                req.setAttribute(ATTRIBUTE_ERROR, errorMessage.toString());
                e.printStackTrace();
            }
            resp.sendRedirect("/do/main");
        }
    }
}
