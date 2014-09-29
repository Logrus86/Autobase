package com.epam.bp.autobase.listener;

import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class RequestListener implements ServletRequestListener {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

    }
}
