package com.epam.bp.autobase.servlet;

import com.epam.bp.autobase.action.Action;
import com.epam.bp.autobase.action.ActionException;
import com.epam.bp.autobase.action.ActionFactory;
import com.epam.bp.autobase.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends javax.servlet.http.HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String actionName = req.getMethod() + req.getPathInfo();
        LOGGER.info("method/actionName: " + actionName);
        Action action = ActionFactory.getAction(actionName);
        if (action == null) {
            LOGGER.error("Action '" + actionName + "' not found. ");
            resp.sendError(404, "Such action not found.");
            return;
        }
        ActionResult result = null;
        try {
            result = action.execute(req);
        } catch (ActionException e) {
            e.printStackTrace();
        }
        if (result.isRedirection()) {
            LOGGER.info("redirect to: " + req.getContextPath() + "/do/" + result.getView());
            resp.sendRedirect(req.getContextPath() + "/do/" + result.getView());
            return;
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/" + result.getView() + ".jsp");
        requestDispatcher.forward(req, resp);
    }
}
