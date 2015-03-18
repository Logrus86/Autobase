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
public class Truck extends Vehicle<Truck> {

    @NotNull
    @Min(1)
    @Max(105)
    @Column(name = "MAX_PAYLOAD")
    private BigDecimal maxPayload;

    @NotNull
    private Boolean enclosed;

    @NotNull
    private Boolean tipper;

    public BigDecimal getMaxPayload() {
        return maxPayload;
    }

    public Truck setMaxPayload(BigDecimal maxPayload) {
        this.maxPayload = maxPayload;
        return this;
    }

    public Boolean isEnclosed() {
        return enclosed;
    }

    public Truck setEnclosed(boolean enclosed) {
        this.enclosed = enclosed;
        return this;
    }

    public Boolean isTipper() {
        return tipper;
    }

    public Truck setTipper(boolean tipper) {
        this.tipper = tipper;
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
        if (getMaxPayload() != null) result.append(", payload=").append(getMaxPayload());
        if (isEnclosed() != null) result.append(", enclosed=").append(isEnclosed());
        if (isTipper() != null) result.append(", tipper=").append(isTipper());
        result.append("}");
        return result.toString();
    }
}
