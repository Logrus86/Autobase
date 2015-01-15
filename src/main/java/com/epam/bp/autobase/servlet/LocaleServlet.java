package com.epam.bp.autobase.servlet;

import com.epam.bp.autobase.util.AttributeSetter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

@WebServlet("/do/locale")
public class LocaleServlet extends HttpServlet{
    private static final String REFERRER = "Referer";
    private static final String LOCALE = "locale";
    private static final String MSG = "Locale was changed to: ";
    @Inject
    AttributeSetter as;
    @Inject
    Logger logger;

    public LocaleServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter(LOCALE);
        HttpSession session = req.getSession();
        Locale locale = new Locale(language);
        session.setAttribute(LOCALE, locale);
        logger.info(MSG + language);
        resp.sendRedirect(req.getHeader(REFERRER));
    }
}
