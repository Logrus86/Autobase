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
        return "Bus {PassSeatsNumber: " + getPassengerSeatsNumber() + ", stPlacesNumber: " + getStandingPlacesNumber() + ", doorsNumber: " + getDoorsNumber() + ", rentPrice: " + getRentPrice() + ", operable: " + isOperable() + ", modelId: " + getModelId() + ", manufactId: " + getManufacturerId() + ", driver: " + getDriverId() + ", prodYear: " + getProductionYear() + ", colorId: " + getColorId() + ", mileage: " + getMileage() + ", fuelType: " + getFuelType() + "}";
    }

}
