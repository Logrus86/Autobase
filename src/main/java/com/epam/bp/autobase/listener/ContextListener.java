package com.epam.bp.autobase.listener;

import com.epam.bp.autobase.pool.ConnectionPool;
import com.epam.bp.autobase.util.AttributeSetter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ResourceBundle;

public class ContextListener implements ServletContextListener {

    private static final String COLOR = "Color";
    private static final String MODEL = "Model";
    private static final String MANUFACTURER = "Manufacturer";
    private static final ResourceBundle RB = ResourceBundle.getBundle("dao");

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String daoType = RB.getString("dao.type");
        if ("JDBC.H2".equals(daoType)) {
            ServletContext context = sce.getServletContext();
            AttributeSetter.setEntityToContext(COLOR, context);
            AttributeSetter.setEntityToContext(MODEL, context);
            AttributeSetter.setEntityToContext(MANUFACTURER, context);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.clearConnectionQueue();
    }
}
