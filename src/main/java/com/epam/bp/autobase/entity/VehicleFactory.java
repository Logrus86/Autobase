package com.epam.bp.autobase.entity;

import java.util.Random;

public class VehicleFactory {
    private static final int VEHICLES_SUBCLASSES_COUNT = 3;

    public static int minMaxRandom(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static int minMaxStepRandom(int min, int max, int step) {
        return (new Random().nextInt((max - min) / step + 1)) * step + min;
    }

    public static boolean boolRandom() {
        return new Random().nextBoolean();
    }

    public static Vehicle createRandomVehicle() {
        int x = minMaxRandom(0, VEHICLES_SUBCLASSES_COUNT - 1);
        switch (x) {
            case 0: {
                BusBuilderRandom bbr = new BusBuilderRandom();
                bbr.createNewBusProduct();
                bbr.buildPassengerSeatsNumber();
                bbr.buildStandingPlacesNumber();
                bbr.buildDoorsNumber();
                bbr.buildRentPrice();
                bbr.buildOperable();
                bbr.buildModel();
                bbr.buildManufacturer();
                bbr.buildDriver();
                bbr.buildProductionYear();
                bbr.buildColor();
                bbr.buildMileage();
                bbr.buildFuelType();
                return bbr.getBus();
            }
            case 1: {
                CarBuilderRandom cbr = new CarBuilderRandom();
                cbr.createNewCarProduct();
                cbr.buildPassengerSeatsNumber();
                cbr.buildWithConditioner();
                cbr.buildDoorsNumber();
                cbr.buildRentPrice();
                cbr.buildOperable();
                cbr.buildModel();
                cbr.buildManufacturer();
                cbr.buildDriver();
                cbr.buildProductionYear();
                cbr.buildColor();
                cbr.buildMileage();
                cbr.buildFuelType();
                return cbr.getCar();
            }
            case 2: {
                TruckBuilderRandom tbr = new TruckBuilderRandom();
                tbr.createNewTruckProduct();
                tbr.buildMaxPayload();
                tbr.buildEnclosed();
                tbr.buildTipper();
                tbr.buildRentPrice();
                tbr.buildOperable();
                tbr.buildModel();
                tbr.buildManufacturer();
                tbr.buildDriver();
                tbr.buildProductionYear();
                tbr.buildColor();
                tbr.buildMileage();
                tbr.buildFuelType();
                return tbr.getTruck();
            }
        }
        return null;
    }
}
