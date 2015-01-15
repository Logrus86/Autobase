package com.epam.bp.autobase.util;

import com.epam.bp.autobase.entity.*;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.event.Reception;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class ListProducer {
    @Inject
    private EntityManager em;
    private List<Color> colors;
    private List<Model> models;
    private List<Manufacturer> manufacturers;

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
    public List<Color> getColors() {
        retrieveAllColors();
        return colors;
    }
    @Produces
    @Named
    public List<Model> getModels() {
        retrieveAllModels();
        return models;
    }
    @Produces
    @Named
    public List<Manufacturer> getManufacturers() {
        retrieveAllManufacturers();
        return manufacturers;
    }

    private void retrieveAllColors() {
        TypedQuery<Color> query = em.createNamedQuery("Color.getAll", Color.class);
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

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Color color) {
        retrieveAllColors(); //TODO use this where specs are being changed
    }
}
