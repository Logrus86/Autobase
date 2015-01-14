package com.epam.bp.autobase.util;

import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.DaoManager;
import com.epam.bp.autobase.dao.H2.*;
import com.epam.bp.autobase.dao.JPA.*;
import com.epam.bp.autobase.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Startup;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Stateless
public class AttributeSetter {
    //this class is used to set entities' attributes to context (rare changing attributes) and to session
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
    public static final String BUS = "Bus";
    public static final String CAR = "Car";
    public static final String TRUCK = "Truck";
    public static final String USER = "user";
    public static final String COLOR = "Color";
    public static final String MODEL = "Model";
    public static final String MANUFACTURER = "Manufacturer";
    public static final String ORDER = "order";
    public static final String DRIVER_VEHICLES = "driverVehicles";
    private static final ResourceBundle rb = ResourceBundle.getBundle("dao");
    private static final String DAO_TYPE = rb.getString("dao.type");

    @Inject
    JpaColorDao colorDao;
    @Inject
    JpaModelDao modelDao;
    @Inject
    JpaManufacturerDao manufacturerDao;
    @Inject
    JpaUserDao userDao;
    @Inject
    JpaVehicleDao vehicleDao;
    @Inject
    OrderDao orderDao;

    public void setToContext(String entityName, ServletContext context) {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                switch (entityName) {
                    case COLOR:
                        if ("H2".equals(DAO_TYPE)) { ColorDao colorDao = daoManager1.getColorDao(); }
                        List<Color> colors = null;
                        if (RU.equals(Locale.getDefault().getLanguage()))
                            colors = colorDao.getAllSortedBy(BaseDao.VALUE_RU);
                        else colors = colorDao.getAllSortedBy(BaseDao.VALUE_EN);
                        context.setAttribute(ATTR_COLORS, colors);
                        break;
                    case MODEL:
                        if ("H2".equals(DAO_TYPE)) { ModelDao modelDao = daoManager1.getModelDao(); }
                        List<Model> models = modelDao.getAllSortedBy(H2ModelDao.VALUE);
                        context.setAttribute(ATTR_MODELS, models);
                        break;
                    case MANUFACTURER:
                        if ("H2".equals(DAO_TYPE)) { ManufacturerDao manufacturerDao = daoManager1.getManufacturerDao(); }
                        List<Manufacturer> manufacturers = manufacturerDao.getAllSortedBy(H2ManufacturerDao.VALUE);
                        context.setAttribute(ATTR_MANUFACTURERS, manufacturers);
                        break;
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AttributeSetterException("Setting " + entityName + "s to Context error while setting attributes", e);
        }
    }

    public void setToSession(String entityName, HttpSession session) {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                switch (entityName) {
                    case COLOR:
                        if ("H2".equals(DAO_TYPE)) { ColorDao colorDao = daoManager1.getColorDao(); }
                        Locale locale = (Locale) session.getAttribute(ATTR_LOCALE);
                        List<Color> colors = null;
                        if (RU.equals(locale.getLanguage()))
                            colors = colorDao.getAllSortedBy(H2ColorDao.VALUE_RU);
                        else colors = colorDao.getAllSortedBy(H2ColorDao.VALUE_EN);
                        session.setAttribute(ATTR_COLORS, colors);
                        break;
                    case BUS: {
                        if ("H2".equals(DAO_TYPE)) { VehicleDao vehicleDao = daoManager1.getVehicleDao(); }
                        List<Vehicle> list =
                                vehicleDao.getListByParameter(H2VehicleDao.VEHICLE_TYPE, String.valueOf(Vehicle.Type.BUS));
                        session.setAttribute(ATTR_BUS_LIST, list);
                        break;
                    }
                    case CAR: {
                        if ("H2".equals(DAO_TYPE)) { VehicleDao vehicleDao = daoManager1.getVehicleDao(); }
                        List<Vehicle> list =
                                vehicleDao.getListByParameter(H2VehicleDao.VEHICLE_TYPE, String.valueOf(Vehicle.Type.CAR));
                        session.setAttribute(ATTR_CAR_LIST, list);
                        break;
                    }
                    case TRUCK: {
                        if ("H2".equals(DAO_TYPE)) { VehicleDao vehicleDao = daoManager1.getVehicleDao(); }
                        List<Vehicle> list =
                                vehicleDao.getListByParameter(H2VehicleDao.VEHICLE_TYPE, String.valueOf(Vehicle.Type.TRUCK));
                        session.setAttribute(ATTR_TRUCK_LIST, list);
                        break;
                    }
                    case USER: {
                        if ("H2".equals(DAO_TYPE)) { UserDao userDao = daoManager1.getUserDao(); }
                        List<User> users = userDao.getAllSortedBy(H2UserDao.USERNAME);
                        session.setAttribute(ATTR_USER_LIST, users);
                        break;
                    }
                    case ORDER: {
                        if ("H2".equals(DAO_TYPE)) { OrderDao orderDao = daoManager1.getOrderDao(); }
                        List<Order> orders = orderDao.getAll();
                        session.setAttribute(ATTR_ORDER_LIST, orders);
                        break;
                    }
                    case DRIVER_VEHICLES: {
                        if ("H2".equals(DAO_TYPE)) { VehicleDao vehicleDao = daoManager1.getVehicleDao(); }
                        User driver = (User) session.getAttribute(USER);
                        List<Vehicle> driverVehicles =
                                vehicleDao.getListByParameter(H2VehicleDao.DRIVER_ID, String.valueOf(driver.getId()));
                        if (!driverVehicles.isEmpty()) session.setAttribute(DRIVER_VEHICLES, driverVehicles);
                        break;
                    }
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            throw new AttributeSetterException("Setting " + entityName + "s to Session error while executing DaoCommand", e);
        }
    }
}
