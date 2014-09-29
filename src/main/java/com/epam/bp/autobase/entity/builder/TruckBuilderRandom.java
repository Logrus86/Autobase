package com.epam.bp.autobase.entity.builder;

import com.epam.bp.autobase.entity.Car;
import com.epam.bp.autobase.entity.Vehicle;

import java.math.BigDecimal;

public class TruckBuilderRandom extends TruckBuilder {

    @Override
    public void buildMaxPayload() {
        truck.setMaxPayload(BigDecimal.valueOf(VehicleFactory.minMaxStepRandom(5000, 30000, 1000)));
    }

    @Override
    public void buildEnclosed() {
        truck.setEnclosed(VehicleFactory.boolRandom());
    }

    @Override
    public void buildTipper() {
        truck.setTipper(VehicleFactory.boolRandom());
    }

    @Override
    public void buildRentPrice() {
        truck.setRentPrice(BigDecimal.valueOf(VehicleFactory.minMaxStepRandom(30000, 200000, 2000)));
    }

    @Override
    public void buildOperable() {
        truck.setOperable(VehicleFactory.boolRandom());
    }

    @Override
    public void buildModel() {
  //      truck.setModel(vhModels[VehicleFactory.minMaxRandom(0, vhModels.length - 1)]);
    }

    @Override
    public void buildManufacturer() {
   //     truck.setManufacturer(vhManufacturers[VehicleFactory.minMaxRandom(0, vhManufacturers.length - 1)]);
    }

    @Override
    public void buildDriver() {
        //     truck.setDriver(DriversBase.getDriverById(VehicleFactory.minMaxRandom(0, DriversBase.getDriversBase().size() - 1)));
    }

    @Override
    public void buildProductionYear() {
        truck.setProductionYear(VehicleFactory.minMaxRandom(1950, 2014));
    }

    @Override
    public void buildColor() {
   //     truck.setColor(vhColors[VehicleFactory.minMaxRandom(0, vhColors.length - 1)]);
    }

    @Override
    public void buildMileage() {
        truck.setMileage(BigDecimal.valueOf(VehicleFactory.minMaxRandom(10000, 200000)));
    }

    @Override
    public void buildFuelType() {
        truck.setFuelType(Vehicle.Fuel.values()[VehicleFactory.minMaxRandom(0, (Car.Fuel.values().length - 1))]);
    }
}
