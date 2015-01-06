package com.epam.bp.autobase.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
public class Bus extends Vehicle {
    @NotNull
    private int passengerSeatsNumber;

    @NotNull
    private int standingPlacesNumber;

    @NotNull
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
        return "Bus {PassSeatsNumber: " + getPassengerSeatsNumber() + ", stPlacesNumber: " + getStandingPlacesNumber()
                + ", doorsNumber: " + getDoorsNumber() + ", rentPrice: " + getRentPrice() + ", operable: " + isOperable()
                + ", model: " + getModel().getValue() + ", manufact: " + getManufacturer().getValue() + ", driver: "
                + getDriverId() + ", prodYear: " + getProductionYear() + ", color: " + getColor().getValueEn() + ", mileage: "
                + getMileage() + ", fuelType: " + getFuelType() + "}";
    }

}
