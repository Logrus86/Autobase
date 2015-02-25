package com.epam.bp.autobase.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "0")
@NamedQuery(name = "Bus.getAll", query = "SELECT b FROM Bus b ORDER BY b.id")
public class Bus extends Vehicle<Bus> {
    @NotNull
    @Min(7)
    @Max(99)
    @Column(name = "PASSENGER_SEATS_NUMBER")
    private Integer passengerSeatsNumber;

    @NotNull
    @Min(7)
    @Max(199)
    @Column(name = "STANDING_PLACES_NUMBER")
    private Integer standingPlacesNumber;

    @NotNull
    @Min(1)
    @Max(9)
    @Column(name = "DOORS_NUMBER")
    private Integer doorsNumber;

    public Integer getPassengerSeatsNumber() {
        return passengerSeatsNumber;
    }

    public Bus setPassengerSeatsNumber(Integer passengerSeatsNumber) {
        this.passengerSeatsNumber = passengerSeatsNumber;
        return this;
    }

    public Integer getStandingPlacesNumber() {
        return standingPlacesNumber;
    }

    public Bus setStandingPlacesNumber(Integer standingPlacesNumber) {
        this.standingPlacesNumber = standingPlacesNumber;
        return this;
    }

    public Integer getDoorsNumber() {
        return doorsNumber;
    }

    public Bus setDoorsNumber(Integer doorsNumber) {
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
