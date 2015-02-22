package com.epam.bp.autobase.jsp.servlet;


import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "do/*")
public class Controller extends HttpServlet {
    @Inject
    Logger logger;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        logger.info("Going to req.getPathInfo(): " + req.getPathInfo());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/" + req.getPathInfo() + ".jsp");
        requestDispatcher.forward(req, resp);
    }
}
