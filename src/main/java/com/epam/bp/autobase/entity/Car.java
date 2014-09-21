package com.epam.bp.autobase.entity;

public class Car extends Vehicle {
    private int passengerSeatsNumber;
    private int doorsNumber;
    private boolean withConditioner;

    public int getPassengerSeatsNumber() {
        return passengerSeatsNumber;
    }

    public Car setPassengerSeatsNumber(int passengerSeatsNumber) {
        this.passengerSeatsNumber = passengerSeatsNumber;
        return this;
    }

    public int getDoorsNumber() {
        return doorsNumber;
    }

    public Car setDoorsNumber(int doorsNumber) {
        this.doorsNumber = doorsNumber;
        return this;
    }

    public boolean isWithConditioner() {
        return withConditioner;
    }

    public Car setWithConditioner(boolean withConditioner) {
        this.withConditioner = withConditioner;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder()
                .append("Car {PassSeatsNumber: ")
                .append(getPassengerSeatsNumber())
                .append(", doorsNumber: ")
                .append(getDoorsNumber())
                .append(", conditioner: ")
                .append(isWithConditioner())
                .append(", rentPrice: ")
                .append(getRentPrice())
                .append(", operable: ")
                .append(isOperable())
                .append(", model: ")
                .append(getModel())
                .append(", manufact: ")
                .append(getManufacturer())
                .append(", driver: ")
                .append(getDriverId())
                .append(", prodYear: ")
                .append(getProductionYear())
                .append(", color: ")
                .append(getColor())
                .append(", mileage: ")
                .append(getMileage())
                .append(", fuelType: ")
                .append(getFuelType())
                .append("}");
        return result.toString();
    }
}
