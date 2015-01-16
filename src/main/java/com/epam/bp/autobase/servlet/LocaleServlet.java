package com.epam.bp.autobase.servlet;

import com.epam.bp.autobase.service.UserService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/do/locale")
public class LocaleServlet extends HttpServlet{
    private static final String PARAMETER_LOCALE = "locale";
    private static final String MSG = "Locale was changed to: ";
    @Inject
    Logger logger;
    @Inject
    UserService us;

    public LocaleServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang_code = req.getParameter(PARAMETER_LOCALE);
        us.setLocaleFromLangCode(lang_code);
        logger.info(MSG + lang_code);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
