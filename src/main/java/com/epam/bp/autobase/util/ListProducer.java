package com.epam.bp.autobase.util;

import com.epam.bp.autobase.model.entity.*;
import com.epam.bp.autobase.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class ListProducer {
    @Inject
    UserService us;
    @Inject
    private EntityManager em;
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
    public List<User> getUserList() {
        if (userList == null) {
            retrieveAllUsers();
        }
        return userList;
    }

    @Produces
    @Named
    @RequestScoped
    public List<Color> getColors() {
        if (colors == null) {
            retrieveAllColors();
        }
        return colors;
    }

    @Produces
    @Named
    @RequestScoped
    public List<Model> getModels() {
        //add retrieve if null only
        retrieveAllModels();
        return models;
    }

    @Produces
    @Named
    @RequestScoped
    public List<Manufacturer> getManufacturers() {
        //add retrieve if null only
        retrieveAllManufacturers();
        return manufacturers;
    }

    @Produces
    @Named
    @RequestScoped
    public List<Order> getOrderList() {
        //add retrieve if null only
        retrieveAllOrders();
        return orderList;
    }

    @Produces
    @Named
    public List<Bus> getBusList() {
        //add retrieve if null only
        retrieveAllBuses();
        return busList;
    }

    @Produces
    @Named
    public List<Car> getCarList() {
        //add retrieve if null only
        retrieveAllCars();
        return carList;
    }

    @Produces
    @Named
    public List<Truck> getTruckList() {
        //add retrieve if null only
        retrieveAllTrucks();
        return truckList;
    }
    
    private void retrieveAllUsers() {
        TypedQuery<User> query = em.createNamedQuery("User.getAll", User.class);
        userList = query.getResultList();
    }

    private void retrieveAllColors() {
        TypedQuery<Color> query;
        if ("ru".equals(us.getLocale().getLanguage())) {
            query = em.createNamedQuery("Color.getAllSortedByRu", Color.class);
        } else {
            query = em.createNamedQuery("Color.getAllSortedByEn", Color.class);
        }
        colors = query.getResultList();
    }

    private void retrieveAllModels() {
        TypedQuery<Model> query = em.createNamedQuery("Model.getAll", Model.class);
        models = query.getResultList();
    }

    private void retrieveAllManufacturers() {
        TypedQuery<Manufacturer> query = em.createNamedQuery("Manufacturer.getAll", Manufacturer.class);
        manufacturers = query.getResultList();
    }

    private void retrieveAllOrders() {
        TypedQuery<Order> query = em.createNamedQuery("Order.getAll", Order.class);
        orderList = query.getResultList();
    }

    private void retrieveAllBuses() {
        TypedQuery<Bus> query = em.createNamedQuery("Bus.getAll", Bus.class);
        busList = query.getResultList();
    }

    private void retrieveAllCars() {
        TypedQuery<Car> query = em.createNamedQuery("Car.getAll", Car.class);
        carList = query.getResultList();
    }

    private void retrieveAllTrucks() {
        TypedQuery<Truck> query = em.createNamedQuery("Truck.getAll", Truck.class);
        truckList = query.getResultList();
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
        retrieveAllBuses();
    }

    public void onCarListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Car car) {
        retrieveAllCars();
    }

    public void onTruckListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Truck truck) {
        retrieveAllTrucks();
    }
}
