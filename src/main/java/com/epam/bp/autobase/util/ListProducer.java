package com.epam.bp.autobase.util;

import com.epam.bp.autobase.entity.*;
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
    private EntityManager em;
    @Inject
    UserService us;
    private List<Color> colors;
    private List<Model> models;
    private List<Manufacturer> manufacturers;
    private List<User> userList;
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
    public List<Color> getColors() {
        retrieveAllColors();
        return colors;
    }
    @Produces
    @Named
    @RequestScoped
    public List<Model> getModels() {
        retrieveAllModels();
        return models;
    }
    @Produces
    @Named
    @RequestScoped
    public List<Manufacturer> getManufacturers() {
        retrieveAllManufacturers();
        return manufacturers;
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

    public void onColorListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Color color) {
        retrieveAllColors();
    }

    public void onUserListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final User user) {
        retrieveAllUsers();
    }
}
