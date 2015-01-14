package com.epam.bp.autobase.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

public class SessionListener implements HttpSessionListener {
    private static final String ATTR_LOCALE = "locale";
    private static final Locale DEFAULT_LOCALE = Locale.getDefault();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(ATTR_LOCALE, DEFAULT_LOCALE);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
