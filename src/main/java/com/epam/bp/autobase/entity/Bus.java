package com.epam.bp.autobase.entity;

public class Bus extends Vehicle {
    private int passengerSeatsNumber;
    private int standingPlacesNumber;
    private int doorsNumber;

    public int getPassengerSeatsNumber() {
        return passengerSeatsNumber;
    }

    public void setPassengerSeatsNumber(int passengerSeatsNumber) {
        this.passengerSeatsNumber = passengerSeatsNumber;
    }

    public int getStandingPlacesNumber() {
        return standingPlacesNumber;
    }

    public void setStandingPlacesNumber(int standingPlacesNumber) {
        this.standingPlacesNumber = standingPlacesNumber;
    }

    public int getDoorsNumber() {
        return doorsNumber;
    }

    public void setDoorsNumber(int doorsNumber) {
        this.doorsNumber = doorsNumber;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder()
                .append("Bus {PassengerSeatsNumber: ")
                .append(getPassengerSeatsNumber())
                .append(", standingPlacesNumber: ")
                .append(getStandingPlacesNumber())
                .append(", doorsNumber: ")
                .append(getDoorsNumber())  //vehicle
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
