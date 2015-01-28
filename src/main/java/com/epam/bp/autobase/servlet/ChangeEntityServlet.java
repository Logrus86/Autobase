package com.epam.bp.autobase.servlet;

import com.epam.bp.autobase.model.dto.ColorDto;
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
        "do/create_user",
        "do/create_color",
        "do/change_user",
        "do/change_color"
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
                } else if (servletPath.equals("/do/change_color")) {
                    changeColor(req, resp);
                }
            }
        }
    }

    private void changeColor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String stringId;
            if (req.getParameter(PARAM_SAVE) == null) {
                stringId = req.getParameter(PARAM_DELETE);
                cs.delete(Integer.valueOf(stringId));
                logger.info("Color with id = " + stringId + " was successfully deleted");
            } else {
                stringId = req.getParameter(PARAM_SAVE);
                ColorDto colorDto = new ColorDto()
                        .setValue_en(req.getParameter("value_en"))
                        .setValue_ru(req.getParameter("value_ru"));
                colorDto.setId(Integer.valueOf(stringId));
                cs.update(colorDto);
                logger.info("Color '" + req.getParameter("value_en") + "' had successfully updated");
            }
        } catch (ServiceException se) {
            logger.error(se.getMessage() + ", " + se.getCause());
        }
        RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/admin_colors.jsp");
        resultView.forward(req, resp);
    }

    private void createColor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ColorDto colorDto = new ColorDto()
                    .setValue_en(req.getParameter("value_en"))
                    .setValue_ru(req.getParameter("value_ru"));
            cs.create(colorDto);
            logger.info("Color created successfully");
        } catch (ServiceException se) {
            logger.error(se.getMessage() + (se.getCause() != null ? ", " + se.getCause() : ""));
        }
        RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/admin_colors.jsp");
        resultView.forward(req, resp);
    }

    private void changeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*//changing user if we are client or driver; if we are admin, do it if PARAM_SAVE parameter not null only
        if (!User.Role.ADMIN.equals(us.getUser().getRole()) || req.getParameter(PARAM_SAVE) != null) {
            try {
                us.update(getServiceMapFromRequest(req));
                logger.info("User '" + req.getParameter("username") + "' was successfully updated");
            } catch (ServiceException se) {
                logger.error(se.getMessage());
            }
        } else {
            String stringId = req.getParameter(PARAM_DELETE);
            if (stringId != null) {
                us.delete(Integer.valueOf(stringId));
                logger.info("User '" + req.getParameter("username") + "' was successfully deleted");
            }
        }*/
        forwardDependsRole(req, resp);
    }

    private void registerUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*try {
            us.create(getServiceMapFromRequest(req));
            logger.info("Newly registered user: " + us.getUser().toString());
            RequestDispatcher resultView = req.getRequestDispatcher("/WEB-INF/jsp/registered.jsp");
            resultView.forward(req, resp);
        } catch (ServiceException se) {
            logger.error(se.getMessage());
            forwardDependsRole(req, resp);
        }*/
    }

    private void forwardDependsRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*RequestDispatcher resultView;
        if (User.Role.CLIENT.equals(us.getUser().getRole())) {
            resultView = req.getRequestDispatcher("/WEB-INF/jsp/cabinet.jsp");
        } else {
            if (User.Role.DRIVER.equals(us.getUser().getRole())) {
                resultView = req.getRequestDispatcher("/WEB-INF/jsp/main_driver.jsp");
            } else {
                if (User.Role.ADMIN.equals(us.getUser().getRole())) {
                    resultView = req.getRequestDispatcher("/WEB-INF/jsp/admin_users.jsp");
                } else resultView = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
            }
        }
        resultView.forward(req, resp);*/
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
