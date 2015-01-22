package com.epam.bp.autobase.model.entity;

import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VEHICLE_TYPE", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Vehicle implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Min(1000)
    @Digits(integer = 6, fraction = 2)
    @Column(name = "RENT_PRICE")
    private BigDecimal rentPrice;

    @NotNull
    @Enumerated
    @Column(name = "VEHICLE_TYPE", insertable = false, updatable = false)
    private Type type;

    @NotNull
    @ManyToOne
    private Color color;

    @NotNull
    @ManyToOne
    private Model model;

    @NotNull
    @ManyToOne
    private Manufacturer manufacturer;

    @Nullable
    @ManyToOne
    private User driver;

    @NotNull
    @Min(1980)
    @Column(name = "PRODUCTION_YEAR")
    private Integer productionYear;

    @NotNull
    @Min(1)
    private BigDecimal mileage;

    @NotNull
    private boolean operable;

    @NotNull
    @Enumerated
    @Column(name = "FUEL_TYPE")
    private Fuel fuelType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Boolean setDriver(User driver) {
        if (driver.getRole().equals(User.Role.DRIVER)) {
            this.driver = driver;
            return true;
        } else return false;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Type getType() {
        return type;
    }

    public Vehicle setType(Type type) {
        this.type = type;
        return this;
    }

    public int getProductionYear() {
        return productionYear;
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

    public Model getModel() {
        return model;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Color getColor() {
        return color;
    }

    public User getDriver() {
        return driver;
    }

    public enum Fuel {
        PETROL, DIESEL, GAS, GAS_PETROL, ELECTRICITY
    }

    public enum Type {
        BUS, CAR, TRUCK
    }
}
