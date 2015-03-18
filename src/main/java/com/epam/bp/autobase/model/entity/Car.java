package com.epam.bp.autobase.model.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "1")
@NamedQuery(name = "Car.getAll", query = "SELECT c FROM Car c ORDER BY c.id")
public class Car extends Vehicle<Car> {
    @NotNull
    @Min(1)
    @Max(6)
    @Column(name = "PASSENGER_SEATS_NUMBER")
    private Integer passengerSeatsNumber;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(name = "DOORS_NUMBER")
    private Integer doorsNumber;

    @Column(name = "CONDITIONER")
    private Boolean withConditioner;

    public Integer getPassengerSeatsNumber() {
        return passengerSeatsNumber;
    }

    public Car setPassengerSeatsNumber(Integer passengerSeatsNumber) {
        this.passengerSeatsNumber = passengerSeatsNumber;
        return this;
    }

    public Integer getDoorsNumber() {
        return doorsNumber;
    }

    public Car setDoorsNumber(Integer doorsNumber) {
        this.doorsNumber = doorsNumber;
        return this;
    }

    public Boolean isWithConditioner() {
        return withConditioner;
    }

    public Car setWithConditioner(boolean withConditioner) {
        this.withConditioner = withConditioner;
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
        if (getDoorsNumber() != null) result.append(", doorsNumber=").append(getDoorsNumber());
        if (isWithConditioner() != null) result.append(", conditioner=").append(isWithConditioner());
        result.append("}");
        return result.toString();
    }
}
