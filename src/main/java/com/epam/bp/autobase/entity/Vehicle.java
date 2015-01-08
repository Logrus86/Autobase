package com.epam.bp.autobase.entity;

import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.dao.H2.DaoManager;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Comparator;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VEHICLE_TYPE", discriminatorType = DiscriminatorType.STRING, length = 5)
public abstract class Vehicle extends Entity {

    @NotNull
    private BigDecimal rentPrice;

    //-----------------------delete these old form later
    @NotNull
    private Integer colorId;
    @NotNull
    private Integer modelId;
    @NotNull
    private Integer manufacturerId;
    @NotNull
    private Integer driverId;
    @Enumerated
    private Type type;
    //--------------------------------new form:
    @ManyToOne
    private Color color;
    @ManyToOne
    private Model model;
    @ManyToOne
    private Manufacturer manufacturer;
    @ManyToOne
    private User driver;
    //---------------------------------------------------
    @NotNull
    private Integer productionYear;

    @NotNull
    private BigDecimal mileage;

    @NotNull
    private boolean operable;

    @Enumerated
    private Fuel fuelType;

    public void setColor(Color color) {
        this.color = color;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setDriver(User driver) {
        this.driver = driver;
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

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Model getModel() {
        final Model[] model = new Model[1];
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                ModelDao modelDao = daoManager1.getModelDao();
                model[0] = modelDao.getById(modelId);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            throw new RuntimeException("Error getting vehicle model from database", e);
        }
        return model[0];
    }

    public Manufacturer getManufacturer() {
        final Manufacturer[] manufacturer = new Manufacturer[1];
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                ManufacturerDao manufacturerDao = daoManager1.getManufacturerDao();
                manufacturer[0] = manufacturerDao.getById(manufacturerId);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            throw new RuntimeException("Error getting vehicle manufacturer from database", e);
        }
        return manufacturer[0];
    }

    public Color getColor() {
        final Color[] color = new Color[1];
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                ColorDao colorDao = daoManager1.getColorDao();
                color[0] = colorDao.getById(colorId);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            throw new RuntimeException("Error getting vehicle color from database", e);
        }
        return color[0];
    }

    public User getDriver() {
        final User[] driver = new User[1];
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                UserDao userDao = daoManager1.getUserDao();
                driver[0] = userDao.getById(driverId);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            throw new RuntimeException("Error getting vehicle driver from database", e);
        }
        return driver[0];
    }

    public enum Fuel {
        PETROL, DIESEL, GAS, GAS_PETROL, ELECTRICITY
    }

    public enum Type {
        BUS, CAR, TRUCK
    }
}
