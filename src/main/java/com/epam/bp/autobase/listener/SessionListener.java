package com.epam.bp.autobase.listener;

import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Enumeration;

public class SessionListener implements HttpSessionListener {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        for (Enumeration<String> attrs = se.getSession().getAttributeNames(); attrs.hasMoreElements(); )
            LOGGER.info(attrs.nextElement());
    }
}
