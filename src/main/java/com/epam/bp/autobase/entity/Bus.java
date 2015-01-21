package com.epam.bp.autobase.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@DiscriminatorValue(value = "BUS")
public class Bus extends Vehicle {
    @NotNull
    @Min(value = 7)
    @Max(99)
    @Column(name = "PASSENGER_SEATS_NUMBER")
    private int passengerSeatsNumber;

    @NotNull
    @Min(7)
    @Max(199)
    @Column(name = "STANDING_PLACES_NUMBER")
    private int standingPlacesNumber;

    @NotNull
    @Min(1)
    @Max(9)
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
        return "Bus {Id: " + getId() + ", PassSeatsNumber: " + getPassengerSeatsNumber() + ", stPlacesNumber: " + getStandingPlacesNumber()
                + ", doorsNumber: " + getDoorsNumber() + ", rentPrice: " + getRentPrice() + ", operable: " + isOperable()
                + ", model: " + getModel().getValue() + ", manufacturer: " + getManufacturer().getValue() + ", driver: "
                + getDriver().getId() + ", prodYear: " + getProductionYear() + ", color: " + getColor().getValue_en() + ", mileage: "
                + getMileage() + ", fuelType: " + getFuelType() + "}";
    }

}
