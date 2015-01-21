package com.epam.bp.autobase.listener;

import com.epam.bp.autobase.service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestListener implements ServletRequestListener {
    @Inject
    UserService us;

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        if (us.getLocale() == null) {
            us.setLocale(sre.getServletRequest().getLocale());
        }
    }
}
