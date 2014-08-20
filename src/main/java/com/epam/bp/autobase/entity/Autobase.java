package com.epam.bp.autobase.entity;

import java.util.ArrayList;
import java.util.List;

public class Autobase implements Cloneable {
    private String title;
    private String address;
    private List<Vehicle> vehicles = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void add(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder()
                .append("Autobase '")
                .append(getTitle())
                .append("'. Address: '")
                .append(getAddress())
                .append("'.\nFull vehicles list:\n");
        int i = 0;
        for (Vehicle vehicle : vehicles) {
            result.append((i + 1))
                    .append(". ")
                    .append(vehicle)
                    .append("\n");
            i++;
        }
        return result.toString();
    }

    @Override
    public Autobase clone() {
        try {
            Autobase clone = (Autobase) super.clone();
            List<Vehicle> cloneVehicles = new ArrayList<>();
            for (Vehicle vehicle : this.vehicles) cloneVehicles.add(vehicle.clone());
            clone.vehicles = cloneVehicles;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
