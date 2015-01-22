package com.epam.bp.autobase.servlet;

import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.service.ColorService;
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
import java.util.Map;

@WebServlet({
        "do/register",
        "do/change_user",
        "do/create_user",
        "do/create_color"
})
public class ChangeEntityServlet extends HttpServlet {
    public static final String PARAM_SAVE = "save";
    public static final String PARAM_DELETE = "delete";
    @Inject
    UserService us;
    @Inject
    ColorService cs;
    @Inject
    Logger logger;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        resultView.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();

        if (servletPath.equals("/do/register")) {
            registerUser(req, resp);
        } else {
            if (servletPath.equals("/do/change_user")) {
                changeUser(req, resp);
            } else {
                if (servletPath.equals("/do/create_color")) {
                    createColor(req, resp);
                }
            }
        }
    }

    private void createColor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            cs.create(req.getParameter("valueEn"), req.getParameter("valueRu"));
            logger.info("Color created successfully");
        } catch (ServiceException se) {
            logger.error(se.getMessage());
        }
        RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/admin_colors.jsp");
        resultView.forward(req, resp);
    }

    private void changeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //changing user if we are client or driver; if we are admin, do it if PARAM_SAVE parameter not null only
        if (!User.Role.ADMIN.equals(us.getSessionUser().getRole()) || req.getParameter(PARAM_SAVE) != null) {
            try {
                us.update(getServiceMapFromRequest(req));
                logger.info("User '" + req.getParameter("username") + "' had successfully updated");
            } catch (ServiceException se) {
                logger.error(se.getMessage());
            }
        }
        String stringId = req.getParameter(PARAM_DELETE);
        if (stringId != null) {
            us.delete(Integer.valueOf(stringId));
            logger.info("User '" + req.getParameter("username") + "' had successfully deleted");
        }
        forwardDependsRole(req, resp);
    }

    private void registerUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            us.create(getServiceMapFromRequest(req));
            logger.info("Newly registered user: " + us.getSessionUser().toString());
            RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/registered.jsp");
            resultView.forward(req, resp);
        } catch (ServiceException se) {
            logger.error(se.getMessage());
            forwardDependsRole(req, resp);
        }
    }

    private void forwardDependsRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher resultView;
        if (User.Role.CLIENT.equals(us.getSessionUser().getRole())) {
            resultView = req.getRequestDispatcher("/WEB-INF/jsp/cabinet.jsp");
        } else {
            if (User.Role.DRIVER.equals(us.getSessionUser().getRole())) {
                resultView = req.getRequestDispatcher("/WEB-INF/jsp/main_driver.jsp");
            } else {
                if (User.Role.ADMIN.equals(us.getSessionUser().getRole())) {
                    resultView = req.getRequestDispatcher("/WEB-INF/jsp/admin_users.jsp");
                } else resultView = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
            }
        }
        resultView.forward(req, resp);
    }

    private Map<String, String> getServiceMapFromRequest(HttpServletRequest request) {
        HashMap<String, String> serviceMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            if ((entry.getKey().equals("save")) || (entry.getKey().equals("delete"))) {
                serviceMap.put("id", entry.getValue()[0]);
            } else serviceMap.put(entry.getKey(), entry.getValue()[0]);
        }
        return serviceMap;
    }
}
