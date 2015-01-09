package com.epam.bp.autobase.action;

import com.epam.bp.autobase.entity.Entity;
import com.epam.bp.autobase.util.AttributeSetter;
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
        HttpSession session = request.getSession();
        String language = request.getParameter(LOCALE);
        Locale locale = new Locale(language);
        session.setAttribute(LOCALE, locale);
        AttributeSetter.setEntityToSession(Entity.COLOR, session);
        LOGGER.info("Locale was changed to: " + language);
        //return to last page with new locale
        String referer = request.getHeader(REFERER);
        //get page name only, without path
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(referer, true);
    }
}
