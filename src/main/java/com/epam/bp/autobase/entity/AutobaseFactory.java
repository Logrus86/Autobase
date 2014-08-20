package com.epam.bp.autobase.entity;

import java.util.List;

public class AutobaseFactory {

    public static Autobase createRandomAutobase(int vehiclesAmount) {
        Autobase autobase = new Autobase();
        autobase.setTitle("Random Autobase");
        autobase.setAddress("Somewhere in the Universe");
        for (int i = 0; i < vehiclesAmount; i++) {
            Vehicle vehicle = VehicleFactory.createRandomVehicle();
            autobase.add(vehicle);
        }
        return autobase;
    }

    public static Autobase createAutobase(String title, String address, List<Vehicle> vehicleList) {
        Autobase autobase = new Autobase();
        autobase.setTitle(title);
        autobase.setAddress(address);
        autobase.setVehicles(vehicleList);
        return autobase;
    }
}
