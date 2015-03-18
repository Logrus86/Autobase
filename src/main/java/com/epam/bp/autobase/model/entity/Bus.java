package com.epam.bp.autobase.model.entity;

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
        StringBuilder result = new StringBuilder();
        result.append("Vehicle{");
        if (getType() != null) result.append("type=").append(getType());
        if (getId() != null) result.append(", id=").append(getId());
        if (getModel() != null) result.append(", model=").append(getModel().getValue());
        if (getManufacturer() != null) result.append(", manufacturer=").append(getManufacturer().getValue());
        if (getColor() != null) result.append(", color=").append(getColor().getValue_en());
        if (getDriver() != null) result.append(", driver=").append(getDriver().getId());
        if (getProductionYear() != null) result.append(", productionYear=").append(getProductionYear());
        if (getMileage() != null) result.append(", mileage=").append(getMileage());
        if (isOperable() != null) result.append(", operable=").append(isOperable());
        if (getFuelType() != null) result.append(", fuelType=").append(getFuelType());
        if (getRentPrice() != null) result.append(", rentPrice=").append(getRentPrice());
        if (getPassengerSeatsNumber() != null) result.append(", passSeatsNumber=").append(getPassengerSeatsNumber());
        if (getStandingPlacesNumber() != null) result.append(", stPlacesNumber=").append(getStandingPlacesNumber());
        if (getDoorsNumber() != null) result.append(", doorsNumber=").append(getDoorsNumber());
        result.append("}");
        return result.toString();
    }

}
