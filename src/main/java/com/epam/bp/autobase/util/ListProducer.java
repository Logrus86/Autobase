package com.epam.bp.autobase.util;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.ejb.SessionState;
import com.epam.bp.autobase.model.dto.*;
import com.epam.bp.autobase.model.entity.*;
import com.epam.bp.autobase.service.*;
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
    UserService us;
    @Inject
    ColorService cs;
    @Inject
    ModelService ms;
    @Inject
    ManufacturerService mfs;
    @Inject
    VehicleService vs;
    @Inject
    OrderService os;
    private List<ColorDto> colors;
    private List<ModelDto> models;
    private List<ManufacturerDto> manufacturers;
    private List<UserDto> userList;
    private List<OrderDto> orderList;
    private List<VehicleDto> busList;
    private List<VehicleDto> carList;
    private List<VehicleDto> truckList;

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
    public List<UserDto> getUserList() throws DaoException {
        if (userList == null) retrieveAllUsers();
        return userList == null ? new ArrayList<>() : userList;
    }

    @Produces
    @Named
    @RequestScoped
    public List<ColorDto> getColors() {
        if (colors == null) retrieveAllColors();
        if (colors != null) {
            if ("ru".equals(ss.getLocale().getLanguage())) colors.sort(Comparator.comparing(ColorDto::getValue_ru));
            else colors.sort(Comparator.comparing(ColorDto::getValue_en));
        }
        return colors == null ? new ArrayList<>() : colors;
    }

    @Produces
    @Named
    @RequestScoped
    public List<ModelDto> getModels() {
        if (models == null) retrieveAllModels();
        return models == null ? new ArrayList<>() : models;
    }

    @Produces
    @Named
    @RequestScoped
    public List<ManufacturerDto> getManufacturers() {
        if (manufacturers == null) retrieveAllManufacturers();
        return manufacturers == null ? new ArrayList<>() : manufacturers;
    }

    @Produces
    @Named
    @RequestScoped
    public List<OrderDto> getOrderList() {
        if (orderList == null) retrieveAllOrders();
        return orderList == null ? new ArrayList<>() : orderList;
    }

    @Produces
    @Named
    public List<VehicleDto> getBusList() {
        if (busList == null) retrieveAllBuses();
        return busList == null ? new ArrayList<>() : busList;
    }

    @Produces
    @Named
    public List<VehicleDto> getCarList() {
        if (carList == null) retrieveAllCars();
        return carList == null ? new ArrayList<>() : carList;
    }

    @Produces
    @Named
    public List<VehicleDto> getTruckList() {
        if (truckList == null) retrieveAllTrucks();
        return truckList == null ? new ArrayList<>() : truckList;
    }

    private void retrieveAllUsers() {
        try {
            userList = us.getAll();
        } catch (Exception e) {
            log.error("Cannot retrieve user list: " + e.getMessage());
        }
    }

    private void retrieveAllColors() {
        try {
            colors = cs.getAll();
        } catch (Exception e) {
            log.error("Cannot retrieve color list: " + e.getMessage());
        }
    }

    private void retrieveAllModels() {
        try {
            models = ms.getAll();
        } catch (Exception e) {
            log.error("Cannot retrieve model list: " + e.getMessage());
        }
    }

    private void retrieveAllManufacturers() {
        try {
            manufacturers = mfs.getAll();
        } catch (Exception e) {
            log.error("Cannot retrieve manufacturer list: " + e.getMessage());
        }
    }

    private void retrieveAllOrders() {
        try {
            orderList = os.getAll();
        } catch (Exception e) {
            log.error("Cannot retrieve order list: " + e.getMessage());
        }
    }

    private void retrieveAllBuses() {
        try {
            busList = vs.getAllBuses();
        } catch (Exception e) {
            log.error("Cannot retrieve bus list: " + e.getMessage());
        }
    }

    private void retrieveAllCars() {
        try {
            carList = vs.getAllCars();
        } catch (Exception e) {
            log.error("Cannot retrieve car list: " + e.getMessage());
        }
    }

    private void retrieveAllTrucks() {
        try {
            truckList = vs.getAllTrucks();
        } catch (Exception e) {
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
                ss.setSessionUser(us.getById(ss.getSessionUser().getId()).buildLazyEntity());
            } catch (Exception e) {
                log.error("Cannot retrieve driver: " + e.getMessage());
            }
    }
}
