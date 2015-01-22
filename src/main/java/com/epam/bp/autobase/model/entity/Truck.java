package com.epam.bp.autobase.model.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@javax.persistence.Entity
@DiscriminatorValue(value = "2")
@NamedQuery(name = "Truck.getAll", query = "SELECT t FROM Truck t ORDER BY t.id")
public class Truck extends Vehicle {

    @NotNull
    @Min(1)
    @Max(105)
    @Column(name = "MAX_PAYLOAD")
    private BigDecimal maxPayload;

    @NotNull
    private boolean enclosed;

    @NotNull
    private boolean tipper;

    public BigDecimal getMaxPayload() {
        return maxPayload;
    }

    public Truck setMaxPayload(BigDecimal maxPayload) {
        this.maxPayload = maxPayload;
        return this;
    }

    public boolean isEnclosed() {
        return enclosed;
    }

    public Truck setEnclosed(boolean enclosed) {
        this.enclosed = enclosed;
        return this;
    }

    public boolean isTipper() {
        return tipper;
    }

    public Truck setTipper(boolean tipper) {
        this.tipper = tipper;
        return this;
    }

    @Override
    public String toString() {
        return "Truck {Id: " + getId() + ", Payload: " + getMaxPayload() + ", enclosed: " + isEnclosed() + ", tipper: " + isTipper() +
                ", rentPrice: " + getRentPrice() + ", operable: " + isOperable() + ", model: " + getModel().getValue() +
                ", manufacturer: " + getManufacturer().getValue() + ", driver: " + getDriver().getId() + ", prodYear: " + getProductionYear() +
                ", color: " + getColor().getValue_en() + ", mileage: " + getMileage() + ", fuelType: " + getFuelType() + "}";
    }
}
