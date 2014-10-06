package com.epam.bp.autobase.util;

import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

public class AttributeSetter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttributeSetter.class);
    private static final String RU = "ru";
    private static final String ATTR_COLORS = "colors";
    private static final String ATTR_MODELS = "models";
    private static final String ATTR_MANUFACTURERS = "manufacturers";
    private static final String ATTR_LOCALE = "locale";
    private static final String ATTR_USER_LIST = "userList";
    private static final String ATTR_BUS_LIST = "busList";
    private static final String ATTR_CAR_LIST = "carList";
    private static final String ATTR_TRUCK_LIST = "truckList";
    private static final String ATTR_ORDER_LIST = "orderList";

    public static void setEntityToContext(String entityName, ServletContext context) throws AttributeSetterException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.executeAndClose(daoManager1 -> {
                switch (entityName) {
                    case Entity.COLOR:
                        ColorDao colorDao = daoManager1.getColorDao();
                        Locale contextLocale = (Locale) context.getAttribute(ATTR_LOCALE);
                        List<Color> colors = null;
                        if (RU.equals(contextLocale.getLanguage()))
                            colors = colorDao.getAllSortedBy(com.epam.bp.autobase.dao.H2.ColorDao.VALUE_RU);
                        else colors = colorDao.getAllSortedBy(com.epam.bp.autobase.dao.H2.ColorDao.VALUE_EN);
                        context.setAttribute(ATTR_COLORS, colors);
                        break;
                    case Entity.MODEL:
                        ModelDao modelDao = daoManager1.getModelDao();
                        List<Model> models = modelDao.getAllSortedBy(com.epam.bp.autobase.dao.H2.ModelDao.VALUE);
                        context.setAttribute(ATTR_MODELS, models);
                        break;
                    case Entity.MANUFACTURER:
                        ManufacturerDao manufacturerDao = daoManager1.getManufacturerDao();
                        List<Manufacturer> manufacturers = manufacturerDao.getAllSortedBy(com.epam.bp.autobase.dao.H2.ManufacturerDao.VALUE);
                        context.setAttribute(ATTR_MANUFACTURERS, manufacturers);
                        break;
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Setting " + entityName + "s to Context error while executing DaoCommand");
            throw new AttributeSetterException("Setting " + entityName + "s to Context error while executing DaoCommand", e);
        }
    }

    public static void setEntityToSession(String entityName, HttpSession session) throws AttributeSetterException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.executeAndClose(daoManager1 -> {
                switch (entityName) {
                    case Entity.BUS : {
                        VehicleDao dao = daoManager1.getVehicleDao();
                        List<Vehicle> list =
                                dao.getListByParameter(com.epam.bp.autobase.dao.H2.VehicleDao.VEHICLE_TYPE, String.valueOf(Vehicle.Type.BUS));
                        session.setAttribute(ATTR_BUS_LIST,list);
                        break;
                    }
                    case Entity.CAR : {
                        VehicleDao dao = daoManager1.getVehicleDao();
                        List<Vehicle> list =
                                dao.getListByParameter(com.epam.bp.autobase.dao.H2.VehicleDao.VEHICLE_TYPE, String.valueOf(Vehicle.Type.CAR));
                        session.setAttribute(ATTR_CAR_LIST,list);
                        break;
                    }
                    case Entity.TRUCK : {
                        VehicleDao dao = daoManager1.getVehicleDao();
                        List<Vehicle> list =
                                dao.getListByParameter(com.epam.bp.autobase.dao.H2.VehicleDao.VEHICLE_TYPE, String.valueOf(Vehicle.Type.TRUCK));
                        session.setAttribute(ATTR_TRUCK_LIST,list);
                        break;
                    }
                    case Entity.USER : {
                        UserDao userDao = daoManager1.getUserDao();
                        List<User> users = userDao.getAllSortedBy(com.epam.bp.autobase.dao.H2.UserDao.USERNAME);
                        session.setAttribute(ATTR_USER_LIST,users);
                        break;
                    }
                    case Entity.ORDER : {
                        OrderDao orderDao = daoManager1.getOrderDao();
                        List<Order> orders = orderDao.getAll();
                        session.setAttribute(ATTR_ORDER_LIST,orders);
                        break;
                    }
                    case Entity.DRIVER_VEHICLES : {
                        VehicleDao vehicleDao = daoManager1.getVehicleDao();
                        User driver = (User) session.getAttribute(Entity.USER);
                        List<Vehicle> driverVehicles =
                                vehicleDao.getListByParameter(com.epam.bp.autobase.dao.H2.VehicleDao.DRIVER_ID, String.valueOf(driver.getId()));
                        if (!driverVehicles.isEmpty()) session.setAttribute(Entity.DRIVER_VEHICLES, driverVehicles);
                        break;
                    }
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Setting " + entityName + "s to Session error while executing DaoCommand");
            throw new AttributeSetterException("Setting " + entityName + "s to Session error while executing DaoCommand", e);
        }
    }
}
