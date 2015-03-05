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

    public boolean isWithConditioner() {
        return withConditioner;
    }

    public Car setWithConditioner(boolean withConditioner) {
        this.withConditioner = withConditioner;
        return this;
    }

    @Override
    public String toString() {
        return "Car {Id: " + getId() + ", PassSeatsNumber: " + getPassengerSeatsNumber() + ", doorsNumber: " + getDoorsNumber()
                + ", conditioner: " + isWithConditioner() + ", rentPrice: " + getRentPrice() + ", operable: "
                + isOperable() + ", model: " + getModel().getValue() + ", manufacturer: " + getManufacturer().getValue()
                + ", driver: " + getDriver().getId() + ", prodYear: " + getProductionYear() + ", color: " + getColor().getValue_en()
                + ", mileage: " + getMileage() + ", fuelType: " + getFuelType() + "}";
    }
}
