package com.epam.bp.autobase.listener;

import com.epam.bp.autobase.entity.Autobase;
import com.epam.bp.autobase.entity.Order;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

public class SessionListener implements HttpSessionListener {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);
    private static final String FUEL_TYPES = "fuelTypes";
    private static final String ROLES = "roles";
    private static final String LIST_USERS = "userList";
    private static final String LIST_CARS = "carList";
    private static final String LIST_BUSES = "busList";
    private static final String LIST_TRUCKS = "truckList";
    private static final String LIST_COLORS = "colorList";
    private static final String LIST_MODELS = "modelList";
    private static final String LIST_MANUFS = "manufacturerList";
    private static final String LOCALE = "locale";
    private static final Locale DEFAULT_LOCALE = Locale.getDefault();
    private static final String STATUSES = "statuses";
    private static final String LIST_ORDERS = "orderList";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(FUEL_TYPES, Vehicle.Fuel.values());
        session.setAttribute(ROLES, User.Role.values());
        session.setAttribute(STATUSES, Order.Status.values());
        Autobase autobase = Autobase.getInstance();
        session.setAttribute(LIST_USERS, autobase.getUserList());
        session.setAttribute(LIST_CARS, autobase.getCarList());
        session.setAttribute(LIST_BUSES, autobase.getBusList());
        session.setAttribute(LIST_TRUCKS, autobase.getTruckList());
        session.setAttribute(LIST_COLORS, autobase.getColorList());
        session.setAttribute(LIST_MODELS, autobase.getModelList());
        session.setAttribute(LIST_MANUFS, autobase.getManufacturerList());
        session.setAttribute(LIST_ORDERS,autobase.getOrderList());
        session.setAttribute(LOCALE, DEFAULT_LOCALE);
        LOGGER.info("New session was created, saving entities lists form Autobase to attributes.");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
