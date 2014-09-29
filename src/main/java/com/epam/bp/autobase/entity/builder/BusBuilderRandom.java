package com.epam.bp.autobase.entity.builder;

import com.epam.bp.autobase.entity.Autobase;
import com.epam.bp.autobase.entity.Car;
import com.epam.bp.autobase.entity.Vehicle;

import java.math.BigDecimal;

public class BusBuilderRandom extends BusBuider {
    private Autobase autobase = Autobase.getInstance();
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
        bus.setModelId(VehicleFactory.minMaxRandom(0, autobase.getModelList().size() - 1));
    }

    @Override
    public void buildManufacturer() {
        bus.setManufacturerId(VehicleFactory.minMaxRandom(0, autobase.getManufacturerList().size() - 1));
    }

    @Override
    public void buildDriver() {
        bus.setDriverId(VehicleFactory.minMaxRandom(0, autobase.getDriverList().size() - 1));
    }

    @Override
    public void buildProductionYear() {
        bus.setProductionYear(VehicleFactory.minMaxRandom(1950, 2014));
    }

    @Override
    public void buildColor() {
        bus.setColorId(VehicleFactory.minMaxRandom(0, autobase.getColorList().size() - 1));
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
