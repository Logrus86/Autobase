package com.epam.bp.autobase.entity;

import java.math.BigDecimal;
import java.time.Year;

public class BusBuilderRandom extends BusBuider {
    @Override
    public void buildPassengerSeatsNumber() {
        bus.setPassengerSeatsNumber(VehicleFactory.minMaxRandom(20, 40));
    }

    @Override
    public void buildStandingPlacesNumber() {
        bus.setStandingPlacesNumber(VehicleFactory.minMaxRandom(30, 70));
    }

    @Override
    public void buildDoorsNumber() {
        bus.setDoorsNumber(VehicleFactory.minMaxRandom(1, 3));
    }

    @Override
    public void buildRentPrice() {
        bus.setRentPrice(BigDecimal.valueOf(VehicleFactory.minMaxStepRandom(20000, 80000, 1000)));
    }

    @Override
    public void buildOperable() {
        bus.setOperable(VehicleFactory.boolRandom());
    }

    @Override
    public void buildModel() {
        bus.setModel(vhModels[VehicleFactory.minMaxRandom(0, vhModels.length - 1)]);
    }

    @Override
    public void buildManufacturer() {
        bus.setManufacturer(vhManufacturers[VehicleFactory.minMaxRandom(0, vhManufacturers.length - 1)]);
    }

    @Override
    public void buildDriver() {
//        bus.setDriver(DriversBase.getDriverById(VehicleFactory.minMaxRandom(0, DriversBase.getDriversBase().size() - 1)));
    }

    @Override
    public void buildProductionYear() {
        bus.setProductionYear(Year.of(VehicleFactory.minMaxRandom(1950, 2014)));
    }

    @Override
    public void buildColor() {
        bus.setColor(vhColors[VehicleFactory.minMaxRandom(0, vhColors.length - 1)]);
    }

    @Override
    public void buildMileage() {
        bus.setMileage(BigDecimal.valueOf(VehicleFactory.minMaxRandom(20000, 400000)));
    }

    @Override
    public void buildFuelType() {
        bus.setFuelType(Vehicle.Fuel.values()[VehicleFactory.minMaxRandom(0, (Car.Fuel.values().length - 1))]);
    }
}
