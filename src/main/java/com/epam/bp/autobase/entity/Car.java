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
        return "Car {PassSeatsNumber: " + getPassengerSeatsNumber() + ", doorsNumber: " + getDoorsNumber() + ", conditioner: " + isWithConditioner() + ", rentPrice: " + getRentPrice() + ", operable: " + isOperable() + ", modelId: " + getModelId() + ", manufactId: " + getManufacturerId() + ", driver: " + getDriverId() + ", prodYear: " + getProductionYear() + ", colorId: " + getColorId() + ", mileage: " + getMileage() + ", fuelType: " + getFuelType() + "}";
    }
}
