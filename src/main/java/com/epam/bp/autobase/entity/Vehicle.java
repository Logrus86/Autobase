package com.epam.bp.autobase.entity;

import com.epam.bp.autobase.dao.Identifiable;

import java.math.BigDecimal;
import java.util.Comparator;

public abstract class Vehicle implements Cloneable, Comparable<Vehicle>, Identifiable<Integer> {
    public static final Comparator<Vehicle> PRICE_COMPARATOR = new Comparator<Vehicle>() {
        @Override
        public int compare(Vehicle vehicle1, Vehicle vehicle2) {
            return vehicle1.rentPrice.compareTo(vehicle2.rentPrice);
        }
    };
    private Integer id;
    private BigDecimal rentPrice;
    private Integer colorId;
    private Integer modelId;
    private Integer manufacturerId;
    private int productionYear;
    private BigDecimal mileage;
    private Fuel fuelType;
    private boolean operable;
    private Integer driverId;
    private final String vehicleType = this.getClass().getSimpleName();

    public String getVehicleType() {
        return vehicleType;
    }

    @Override
    public java.lang.Integer getId() {
        return id;
    }

    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public String getFuelType() {
        return fuelType.toString();
    }

    public void setFuelType(Fuel fuelType) {
        this.fuelType = fuelType;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
    }

    public boolean isOperable() {
        return operable;
    }

    public void setOperable(boolean operable) {
        this.operable = operable;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public boolean setDriverId (Integer driverId) {
        this.driverId = driverId;
        return true;
    }

    public Model getModel() {
        Model result;
        Autobase autobase = Autobase.getInstance();
        result = autobase.getModelById(modelId);
        return result;
    }

    public Manufacturer getManufacturer() {
        Manufacturer result;
        Autobase autobase = Autobase.getInstance();
        result = autobase.getManufacturerById(manufacturerId);
        return result;
    }

    public Color getColor() {
        Color result;
        Autobase autobase = Autobase.getInstance();
        result = autobase.getColorById(colorId);
        return result;
    }

    @Override
    protected Vehicle clone() {
        try {
            return (Vehicle) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public int compareTo(Vehicle another) {
        return this.rentPrice.compareTo(another.rentPrice);
    }

    public enum Fuel {
        PETROL, DIESEL, GAS, GAS_PETROL, ELECTRICITY
    }
}
