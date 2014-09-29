package com.epam.bp.autobase.action;

import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLocaleAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ChangeLocaleAction.class);
    private static final String REFERER = "Referer";
    private static final String LOCALE = "locale";

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        String referer = request.getHeader(REFERER);
        String language = request.getParameter(LOCALE);
        HttpSession session = request.getSession();
        Locale locale = new Locale(language);
        LOGGER.info("Locale was changed to: " + language);
        //changing locale for jsp:
        session.setAttribute(LOCALE, locale);
        //changing locale for jvm:
        Locale.setDefault(locale);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(referer, true);
    }
}
