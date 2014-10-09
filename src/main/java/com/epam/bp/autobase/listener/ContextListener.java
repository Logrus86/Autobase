package com.epam.bp.autobase.listener;

import com.epam.bp.autobase.entity.Entity;
import com.epam.bp.autobase.entity.Order;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.pool.ConnectionPool;
import com.epam.bp.autobase.util.AttributeSetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Locale;

public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContextListener.class);
/*    private static final ResourceBundle RB = ResourceBundle.getBundle("db");
    private static final String[] TABLE_LIST = RB.getString("db.tables").split(",");*/
    private static final String ATTR_FUEL_TYPES = "fuelTypes";
    private static final String ATTR_VEHICLE_TYPES = "vehicleTypes";
    private static final String ATTR_ROLES = "roles";
    private static final String ATTR_STATUSES = "statuses";
    private static final String ATTR_LOCALE = "locale";
    private static final Locale DEFAULT_LOCALE = Locale.getDefault();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        /*//checking connection to the database and required tables
        try {
            ConnectionPool.ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            DatabaseMetaData meta = connection.getMetaData();
            Boolean allExists = true;
            for (String tableNamePattern : TABLE_LIST) {
                ResultSet result = meta.getTables(null, null, tableNamePattern, null);
                if (!result.next()) {
                    LOGGER.info("Table " + tableNamePattern + " wasn't found in the database.");
                    allExists = false;
                }
                result.close();
            }
            if (!allExists) {
                LOGGER.info("Creating required tables.");

                LOGGER.info("Filling tables with required data.");

            } else LOGGER.info("All tables in the database are exist, proceed.");
            connection.close();
        } catch (Exception e) {
            throw new ConnectionPoolException("Error while connecting to the database.");
        }*/

        context.setAttribute(ATTR_LOCALE, DEFAULT_LOCALE);
        context.setAttribute(ATTR_FUEL_TYPES, Vehicle.Fuel.values());
        context.setAttribute(ATTR_VEHICLE_TYPES, Vehicle.Type.values());
        context.setAttribute(ATTR_ROLES, User.Role.values());
        context.setAttribute(ATTR_STATUSES, Order.Status.values());
        AttributeSetter.setEntityToContext(Entity.MODEL, context);
        AttributeSetter.setEntityToContext(Entity.COLOR, context);
        AttributeSetter.setEntityToContext(Entity.MANUFACTURER, context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.clearConnectionQueue();
    }
}
