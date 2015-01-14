package com.epam.bp.autobase.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@DiscriminatorValue(value = "BUS")
public class Bus extends Vehicle {
    @NotNull
    @Column(name = "PASSENGER_SEATS_NUMBER")
    private int passengerSeatsNumber;

    @NotNull
    @Column(name = "STANDING_PLACES_NUMBER")
    private int standingPlacesNumber;

    @NotNull
    @Column(name = "DOORS_NUMBER")
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
                + getDriverId() + ", prodYear: " + getProductionYear() + ", color: " + getColor().getValue_en() + ", mileage: "
                + getMileage() + ", fuelType: " + getFuelType() + "}";
    }

}
