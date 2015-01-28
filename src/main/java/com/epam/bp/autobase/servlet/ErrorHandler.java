package com.epam.bp.autobase.servlet;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/error")
public class ErrorHandler extends HttpServlet {
    @Inject
    Logger logger;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
        String message = (String) req.getAttribute("javax.servlet.error.message");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        logger.error("Status code: {" + statusCode + "}, message: {" + message + "}, requestUri: {" + requestUri + "}, servletName: {" + servletName + "}.");
        req.setAttribute("statusCode", statusCode);
        req.setAttribute("message", message);
        req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
    }

}
