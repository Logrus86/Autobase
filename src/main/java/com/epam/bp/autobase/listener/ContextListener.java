package com.epam.bp.autobase.listener;

import com.epam.bp.autobase.entity.Order;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.pool.ConnectionPool;
import com.epam.bp.autobase.util.AttributeSetter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    private static final String COLOR = "Color";
    private static final String MODEL = "Model";
    private static final String MANUFACTURER = "Manufacturer";

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();
        AttributeSetter.setEntityToContext(COLOR, context);
        AttributeSetter.setEntityToContext(MODEL, context);
        AttributeSetter.setEntityToContext(MANUFACTURER, context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.clearConnectionQueue();
    }
}
