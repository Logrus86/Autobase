package com.epam.bp.autobase.entity;

import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.dao.H2.DaoManager;

import java.math.BigDecimal;

public abstract class Vehicle extends Entity {
    private BigDecimal rentPrice;
    private Integer colorId;
    private Integer modelId;
    private Integer manufacturerId;
    private Integer driverId;
    private int productionYear;
    private BigDecimal mileage;
    private Fuel fuelType;
    private boolean operable;
    private Color color;
    private Model model;
    private Manufacturer manufacturer;
    private User driver;

    private Type type;

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

    public void setDriverId (Integer driverId) {
        this.driverId = driverId;
    }

    public Model getModel() {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                ModelDao modelDao = daoManager1.getModelDao();
                model = modelDao.getById(modelId);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            throw new RuntimeException("Error getting vehicle model from database", e);
        }
        return model;
    }

    public Manufacturer getManufacturer() {
            try {
                DaoFactory daoFactory = DaoFactory.getInstance();
                DaoManager daoManager = daoFactory.getDaoManager();
                daoManager.transactionAndClose(daoManager1 -> {
                    ManufacturerDao manufacturerDao = daoManager1.getManufacturerDao();
                    manufacturer = manufacturerDao.getById(manufacturerId);
                });
                daoFactory.releaseContext();
            } catch (Exception e) {
                throw new RuntimeException("Error getting vehicle manufacturer from database", e);
            }
        return manufacturer;
    }

    public Color getColor() {
            try {
                DaoFactory daoFactory = DaoFactory.getInstance();
                DaoManager daoManager = daoFactory.getDaoManager();
                daoManager.transactionAndClose(daoManager1 -> {
                    ColorDao colorDao = daoManager1.getColorDao();
                    color = colorDao.getById(colorId);
                });
                daoFactory.releaseContext();
            } catch (Exception e) {
                throw new RuntimeException("Error getting vehicle color from database", e);
            }
        return color;
    }

    public User getDriver() {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(daoManager1 -> {
                UserDao userDao = daoManager1.getUserDao();
                driver = userDao.getById(driverId);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            throw new RuntimeException("Error getting vehicle driver from database", e);
        }
        return driver;
    }

    public enum Fuel {
        PETROL, DIESEL, GAS, GAS_PETROL, ELECTRICITY
    }

    public enum Type {
        BUS, CAR, TRUCK
    }
}
