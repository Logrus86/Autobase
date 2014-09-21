package com.epam.bp.autobase.entity;

public class Bus extends Vehicle {
    private int passengerSeatsNumber;
    private int standingPlacesNumber;
    private int doorsNumber;

    public int getPassengerSeatsNumber() {
        return passengerSeatsNumber;
    }

    public Bus setPassengerSeatsNumber(int passengerSeatsNumber) {
        this.passengerSeatsNumber = passengerSeatsNumber;
        return this;
    }

    public int getStandingPlacesNumber() {
        return standingPlacesNumber;
    }

    public Bus setStandingPlacesNumber(int standingPlacesNumber) {
        this.standingPlacesNumber = standingPlacesNumber;
        return this;
    }

    public int getDoorsNumber() {
        return doorsNumber;
    }

    public Bus setDoorsNumber(int doorsNumber) {
        this.doorsNumber = doorsNumber;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder()
                .append("Bus {PassSeatsNumber: ")
                .append(getPassengerSeatsNumber())
                .append(", stPlacesNumber: ")
                .append(getStandingPlacesNumber())
                .append(", doorsNumber: ")
                .append(getDoorsNumber())  //vehicle
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
