package com.epam.bp.autobase.entity.builder;

import com.epam.bp.autobase.entity.Bus;

public abstract class BusBuider extends VehicleBuilder {
    protected static Bus bus;

    public Bus getBus() {
        return bus;
    }

    public static Bus createNewBusProduct() {
        return bus;
    }

    public abstract void buildPassengerSeatsNumber();

    public abstract void buildStandingPlacesNumber();

    public abstract void buildDoorsNumber();
}
