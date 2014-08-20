package com.epam.bp.autobase.entity;

import java.math.BigDecimal;
import java.time.Year;

public class CarBuilderRandom extends CarBuilder {

    @Override
    public void buildPassengerSeatsNumber() {
        car.setPassengerSeatsNumber(VehicleFactory.minMaxRandom(1, 4));
    }

    @Override
    public void buildDoorsNumber() {
        car.setDoorsNumber(VehicleFactory.minMaxRandom(2, 4));
    }

    @Override
    public void buildWithConditioner() {
        car.setWithConditioner(VehicleFactory.boolRandom());
    }

    @Override
    public void buildRentPrice() {
        car.setRentPrice(BigDecimal.valueOf(VehicleFactory.minMaxStepRandom(10000, 50000, 500)));
    }

    @Override
    public void buildOperable() {
        car.setOperable(VehicleFactory.boolRandom());
    }

    @Override
    public void buildModel() {
        car.setModel("Model_" + (String.valueOf(VehicleFactory.minMaxRandom(1, 100))));
        car.setModel(vhModels[VehicleFactory.minMaxRandom(0, vhModels.length - 1)]);
    }

    @Override
    public void buildManufacturer() {
        car.setManufacturer(vhManufacturers[VehicleFactory.minMaxRandom(0, vhManufacturers.length - 1)]);
    }

    @Override
    public void buildDriver() {
        //     car.setDriver(DriversBase.getDriverById(VehicleFactory.minMaxRandom(0, DriversBase.getDriversBase().size() - 1)));
    }

    @Override
    public void buildProductionYear() {
        car.setProductionYear(Year.of(VehicleFactory.minMaxRandom(1950, 2014)));
    }

    @Override
    public void buildColor() {
        car.setColor(vhColors[VehicleFactory.minMaxRandom(0, vhColors.length - 1)]);
    }

    @Override
    public void buildMileage() {
        car.setMileage(BigDecimal.valueOf(VehicleFactory.minMaxRandom(0, 200000)));
    }

    @Override
    public void buildFuelType() {
        car.setFuelType(Vehicle.Fuel.values()[VehicleFactory.minMaxRandom(0, (Car.Fuel.values().length - 1))]);
    }
}
