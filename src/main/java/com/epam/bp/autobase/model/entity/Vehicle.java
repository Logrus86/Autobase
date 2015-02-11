package com.epam.bp.autobase.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VEHICLE_TYPE", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Vehicle<T extends Vehicle> implements Identifiable<Vehicle> {

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
    private Boolean operable;

    @NotNull
    @Enumerated
    @Column(name = "FUEL_TYPE")
    private Fuel fuelType;

    public Integer getId() {
        return id;
    }

    @Override
    public T setId(Integer id) {
        return (T) this;
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

    public Vehicle setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public Vehicle setMileage(BigDecimal mileage) {
        this.mileage = mileage;
        return this;
    }

    public Fuel getFuelType() {
        return fuelType;
    }

    public Vehicle setFuelType(Fuel fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public Vehicle setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
        return this;
    }

    public boolean isOperable() {
        return operable;
    }

    public Vehicle setOperable(boolean operable) {
        this.operable = operable;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public Vehicle setModel(Model model) {
        this.model = model;
        return this;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Vehicle setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public Color getColor() {
        return color;
    }

    public Vehicle setColor(Color color) {
        this.color = color;
        return this;
    }

    public User getDriver() {
        return driver;
    }

    public Vehicle setDriver(User driver) {
        if (driver.getRole().equals(User.Role.DRIVER)) {
            this.driver = driver;
        }
        return this;
    }

    public enum Fuel {
        PETROL, DIESEL, GAS, GAS_PETROL, ELECTRICITY
    }

    public enum Type {
        BUS, CAR, TRUCK
    }
}
