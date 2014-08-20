package com.epam.bp.autobase.entity;

public class Car extends Vehicle {
    private int passengerSeatsNumber;
    private int doorsNumber;
    private boolean withConditioner;

    public int getPassengerSeatsNumber() {
        return passengerSeatsNumber;
    }

    public void setPassengerSeatsNumber(int passengerSeatsNumber) {
        this.passengerSeatsNumber = passengerSeatsNumber;
    }

    public int getDoorsNumber() {
        return doorsNumber;
    }

    public void setDoorsNumber(int doorsNumber) {
        this.doorsNumber = doorsNumber;
    }

    public boolean isWithConditioner() {
        return withConditioner;
    }

    public void setWithConditioner(boolean withConditioner) {
        this.withConditioner = withConditioner;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder()
                .append("Car {PassengerSeatsNumber: ")
                .append(getPassengerSeatsNumber())
                .append(", doorsNumber: ")
                .append(getDoorsNumber())
                .append(", withConditioner: ")
                .append(isWithConditioner())
                .append(", rentPrice: ")
                .append(getRentPrice())
                .append(", operable: ")
                .append(isOperable())
                .append(", model: ")
                .append(getModel())
                .append(", manufacturer: ")
                .append(getManufacturer())
                .append(", driver: ")
                .append(getDriver())
                .append(", productionYear: ")
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
