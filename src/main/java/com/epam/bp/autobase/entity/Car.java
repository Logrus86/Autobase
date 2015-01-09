package com.epam.bp.autobase.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@DiscriminatorValue(value = "CAR")
public class Car extends Vehicle {
    @NotNull
    @Column(name = "PASSENGER_SEATS_NUMBER")
    private int passengerSeatsNumber;

    @NotNull
    @Column(name = "DOORS_NUMBER")
    private int doorsNumber;

    @NotNull
    @Column(name = "CONDITIONER")
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
        return "Car {PassSeatsNumber: " + getPassengerSeatsNumber() + ", doorsNumber: " + getDoorsNumber()
                + ", conditioner: " + isWithConditioner() + ", rentPrice: " + getRentPrice() + ", operable: "
                + isOperable() + ", model: " + getModel().getValue() + ", manufacturer: " + getManufacturer().getValue()
                + ", driver: " + getDriverId() + ", prodYear: " + getProductionYear() + ", color: " + getColor().getValueEn()
                + ", mileage: " + getMileage() + ", fuelType: " + getFuelType() + "}";
    }
}
