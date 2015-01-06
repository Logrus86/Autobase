package com.epam.bp.autobase.action;

import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLocaleAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ChangeLocaleAction.class);
    private static final String REFERRER = "Referer";
    private static final String LOCALE = "locale";
    private static final String MSG = "Locale was changed to: ";

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        String language = request.getParameter(LOCALE);
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        Locale locale = new Locale(language);
        context.setAttribute(LOCALE, locale);
        LOGGER.info(MSG + language);
        String referrer = request.getHeader(REFERRER);
        referrer = referrer.substring(referrer.lastIndexOf("/") + 1, referrer.length());
        return new ActionResult(referrer, true);
    }
}
