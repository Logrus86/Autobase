package com.epam.bp.autobase.entity;

import java.math.BigDecimal;

public class Truck extends Vehicle {
    private BigDecimal maxPayload;
    private boolean enclosed;
    private boolean tipper;

    public BigDecimal getMaxPayload() {
        return maxPayload;
    }

    public void setMaxPayload(BigDecimal maxPayload) {
        this.maxPayload = maxPayload;
    }

    public boolean isEnclosed() {
        return enclosed;
    }

    public void setEnclosed(boolean enclosed) {
        this.enclosed = enclosed;
    }

    public boolean isTipper() {
        return tipper;
    }

    public void setTipper(boolean tipper) {
        this.tipper = tipper;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder()
                .append("Truck {maxPayload: ")
                .append(getMaxPayload())
                .append(", enclosed: ")
                .append(isEnclosed())
                .append(", tipper: ")
                .append(isTipper())
                .append(", rentPrice: ")
                .append(getRentPrice())
                .append(", operable: ")
                .append(isOperable())
                .append(", model: ")
                .append(getModel())
                .append(", manufacturer: ")
                .append(getManufacturer())
                .append(", driver: ")
                .append(getDriver())
                .append(", productionYear: ")
                .append(getProductionYear())
                .append(", color: ")
                .append(getColor())
                .append(", mileage: ")
                .append(getMileage())
                .append(", fuelType: ")
                .append(getFuelType())
                .append("}");
        return result.toString();
    }
}
