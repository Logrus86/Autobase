package com.epam.bp.autobase.entity.builder;

import com.epam.bp.autobase.entity.Car;

public abstract class CarBuilder extends VehicleBuilder {
    protected Car car;

    public Car getCar() {
        return car;
    }

    public void createNewCarProduct() {
        car = new Car();
    }

    public abstract void buildPassengerSeatsNumber();

    public abstract void buildDoorsNumber();

    public abstract void buildWithConditioner();
}
