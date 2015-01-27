package com.epam.bp.autobase.listener;

import com.epam.bp.autobase.service.SessionState;

import javax.inject.Inject;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestListener implements ServletRequestListener {
    @Inject
    SessionState ss;

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        if (ss.getLocale() == null) {
            ss.setLocale(sre.getServletRequest().getLocale());
        }
    }
}
