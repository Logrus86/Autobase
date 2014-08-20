package com.epam.bp.autobase.entity;

public abstract class BusBuider extends VehicleBuilder {
    protected Bus bus;

    public Bus getBus() {
        return bus;
    }

    public void createNewBusProduct() {
        bus = new Bus();
    }

    public abstract void buildPassengerSeatsNumber();

    public abstract void buildStandingPlacesNumber();

    public abstract void buildDoorsNumber();
}
