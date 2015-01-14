package com.epam.bp.autobase.listener;

import com.epam.bp.autobase.dao.BaseDao;
import com.epam.bp.autobase.dao.ColorDao;
import com.epam.bp.autobase.dao.JPA.JpaColorDao;
import com.epam.bp.autobase.entity.Color;
import com.epam.bp.autobase.pool.ConnectionPool;
import com.epam.bp.autobase.util.AttributeSetter;
import com.epam.bp.autobase.util.ListProducer;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.clearConnectionQueue();
    }
}
