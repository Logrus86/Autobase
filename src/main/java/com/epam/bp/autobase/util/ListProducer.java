package com.epam.bp.autobase.util;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.dao.hibernate.Hibernate;
import com.epam.bp.autobase.entity.*;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class ListProducer {
    @Inject
    SessionState ss;
    @Inject
    private EntityManager em;
    @Inject
    Logger log;
    @Inject
    @Hibernate
    private UserDao userDao;
    @Inject
    @Hibernate
    private OrderDao orderDao;
    @Inject
    @Hibernate
    private ColorDao colorDao;
    @Inject
    @Hibernate
    private ModelDao modelDao;
    @Inject
    @Hibernate
    private ManufacturerDao manufacturerDao;
    @Inject
    @Hibernate
    private VehicleDao vehicleDao;
    private List<Color> colors;
    private List<Model> models;
    private List<Manufacturer> manufacturers;
    private List<User> userList;
    private List<Order> orderList;
    private List<Bus> busList;
    private List<Car> carList;
    private List<Truck> truckList;

    @Produces
    @Named
    public Vehicle.Fuel[] getFuelTypes() {
        return Vehicle.Fuel.values();
    }

    @Produces
    @Named
    public Vehicle.Type[] getVehicleTypes() {
        return Vehicle.Type.values();
    }

    @Produces
    @Named
    public User.Role[] getRoles() {
        return User.Role.values();
    }

    @Produces
    @Named
    public Order.Status[] getStatuses() {
        return Order.Status.values();
    }

    @Produces
    @Named
    @RequestScoped
    public List<User> getUserList() throws DaoException {
        if (userList == null) retrieveAllUsers();
        return userList == null ? new ArrayList<>() : userList;
    }

    @Produces
    @Named
    @RequestScoped
    public List<Color> getColors() {
        if (colors == null) retrieveAllColors();
        if (colors != null) {
            if ("ru".equals(ss.getLocale().getLanguage())) colors.sort(Comparator.comparing(Color::getValue_ru));
            else colors.sort(Comparator.comparing(Color::getValue_en));
        }
        return colors == null ? new ArrayList<>() : colors;
    }

    @Produces
    @Named
    @RequestScoped
    public List<Model> getModels() {
        if (models == null) retrieveAllModels();
        return models == null ? new ArrayList<>() : models;
    }

    @Produces
    @Named
    @RequestScoped
    public List<Manufacturer> getManufacturers() {
        if (manufacturers == null) retrieveAllManufacturers();
        return manufacturers == null ? new ArrayList<>() : manufacturers;
    }

    @Produces
    @Named
    @RequestScoped
    public List<Order> getOrderList() {
        if (orderList == null) retrieveAllOrders();
        return orderList == null ? new ArrayList<>() : orderList;
    }

    @Produces
    @Named
    public List<Bus> getBusList() {
        if (busList == null) retrieveAllBuses();
        return busList == null ? new ArrayList<>() : busList;
    }

    @Produces
    @Named
    public List<Car> getCarList() {
        if (carList == null) retrieveAllCars();
        return carList == null ? new ArrayList<>() : carList;
    }

    @Produces
    @Named
    public List<Truck> getTruckList() {
        if (truckList == null) retrieveAllTrucks();
        return truckList == null ? new ArrayList<>() : truckList;
    }

    private void retrieveAllUsers() {
        try {
            userList = userDao.getAll();
        } catch (DaoException e) {
            log.error("Cannot retrieve user list: " + e.getMessage());
        }
    }

    private void retrieveAllColors() {
        try {
            colors = colorDao.getAll();
        } catch (DaoException e) {
            log.error("Cannot retrieve color list: " + e.getMessage());
        }
    }

    private void retrieveAllModels() {
        try {
            models = modelDao.getAll();
        } catch (DaoException e) {
            log.error("Cannot retrieve model list: " + e.getMessage());
        }
    }

    private void retrieveAllManufacturers() {
        try {
            manufacturers = manufacturerDao.getAll();
        } catch (DaoException e) {
            log.error("Cannot retrieve manufacturer list: " + e.getMessage());
        }
    }

    private void retrieveAllOrders() {
        try {
            orderList = orderDao.getAll();
        } catch (DaoException e) {
            log.error("Cannot retrieve order list: " + e.getMessage());
        }
    }

    private void retrieveAllBuses() {
        try {
            busList = vehicleDao.getAllBuses();
        } catch (DaoException e) {
            log.error("Cannot retrieve bus list: " + e.getMessage());
        }
    }

    private void retrieveAllCars() {
        try {
            carList = vehicleDao.getAllCars();
        } catch (DaoException e) {
            log.error("Cannot retrieve car list: " + e.getMessage());
        }
    }

    private void retrieveAllTrucks() {
        try {
            truckList = vehicleDao.getAllTrucks();
        } catch (DaoException e) {
            log.error("Cannot retrieve truck list: " + e.getMessage());
        }
    }

    public void onUserListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final User user) {
        retrieveAllUsers();
    }

    public void onColorListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Color color) {
        retrieveAllColors();
    }

    public void onModelListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Model model) {
        retrieveAllModels();
    }

    public void onManufacturerListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Manufacturer manufacturer) {
        retrieveAllManufacturers();
    }

    public void onOrderListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Order order) {
        retrieveAllOrders();
    }

    public void onBusListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Bus bus) {
        retrieveAllVehicle();
    }

    public void onCarListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Car car) {
        retrieveAllVehicle();
    }

    public void onTruckListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Truck truck) {
        retrieveAllVehicle();
    }

    public void onVehicleListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Vehicle vehicle) {
        retrieveAllVehicle();
    }

    private void retrieveAllVehicle() {
        retrieveAllBuses();
        retrieveAllCars();
        retrieveAllTrucks();
        if (ss.getSessionUser().getRole().equals(User.Role.DRIVER))
            try {
                ss.setSessionUser(userDao.getById(ss.getSessionUser().getId()));
            } catch (DaoException e) {
                log.error("Cannot retrieve driver: " + e.getMessage());
            }
    }
}
