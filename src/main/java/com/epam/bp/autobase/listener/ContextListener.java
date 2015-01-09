package com.epam.bp.autobase.listener;

import com.epam.bp.autobase.entity.Entity;
import com.epam.bp.autobase.entity.Order;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.pool.ConnectionPool;
import com.epam.bp.autobase.util.AttributeSetter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    private static final String ATTR_FUEL_TYPES = "fuelTypes";
    private static final String ATTR_VEHICLE_TYPES = "vehicleTypes";
    private static final String ATTR_ROLES = "roles";
    private static final String ATTR_STATUSES = "statuses";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute(ATTR_FUEL_TYPES, Vehicle.Fuel.values());
        context.setAttribute(ATTR_VEHICLE_TYPES, Vehicle.Type.values());
        context.setAttribute(ATTR_ROLES, User.Role.values());
        context.setAttribute(ATTR_STATUSES, Order.Status.values());
        AttributeSetter.setEntityToContext(Entity.COLOR, context);
        AttributeSetter.setEntityToContext(Entity.MODEL, context);
        AttributeSetter.setEntityToContext(Entity.MANUFACTURER, context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.clearConnectionQueue();
    }
}
