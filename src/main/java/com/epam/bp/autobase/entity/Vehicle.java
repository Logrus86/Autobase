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
    private String model;
    private String manufacturer;
    private int productionYear;
    private String color;
    private BigDecimal mileage;
    private Fuel fuelType;
    private boolean operable;
    private Integer driverId;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public Fuel getFuelType() {
        return fuelType;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public boolean setDriverId (Integer driverId) {
        this.driverId = driverId;
        return true;
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
