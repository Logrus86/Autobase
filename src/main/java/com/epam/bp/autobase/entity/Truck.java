package com.epam.bp.autobase.entity;

import java.math.BigDecimal;

public class Truck extends Vehicle {
    private BigDecimal maxPayload;
    private boolean enclosed;
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
        return "Truck {Payload: " + getMaxPayload() + ", enclosed: " + isEnclosed() + ", tipper: " + isTipper() +
                ", rentPrice: " + getRentPrice() + ", operable: " + isOperable() + ", model: " + getModel().getValue() +
                ", manufact: " + getManufacturer().getValue() + ", driver: " + getDriverId() + ", prodYear: " + getProductionYear() +
                ", color: " + getColor().getValueEn() + ", mileage: " + getMileage() + ", fuelType: " + getFuelType() + "}";
    }
}
