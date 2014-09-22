package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.dao.H2.*;
import com.epam.bp.autobase.pool.ConnectionPool;

import java.util.ResourceBundle;

public class DaoManager {
    private ConnectionPool.ProxyConnection connection;
    private static final String DAOTYPE = ResourceBundle.getBundle("db").getString("dao.type");
    private UserDao userDao;
    private VehicleDao vehicleDao;
    private ColorDao colorDao;
    private ModelDao modelDao;
    private ManufacturerDao manufacturerDao;

    public DaoManager(ConnectionPool.ProxyConnection connection) {
        this.connection = connection;
    }

    public UserDao getUserDao() {
        if (this.userDao == null) {
            if (DAOTYPE.equals("h2")) this.userDao = new H2UserDao(this.connection);
        }
        return userDao;
    }

    public VehicleDao getVehicleDao() {
        if (this.vehicleDao == null) {
            if (DAOTYPE.equals("h2")) this.vehicleDao = new H2VehicleDao(this.connection);
        }
        return vehicleDao;
    }

    public ColorDao getColorDao() {
        if (this.colorDao == null) {
            if (DAOTYPE.equals("h2")) this.colorDao = new H2ColorDao(this.connection);
        }
        return colorDao;
    }

    public ModelDao getModelDao() {
        if (this.modelDao == null) {
            if (DAOTYPE.equals("h2")) this.modelDao = new H2ModelDao(this.connection);
        }
        return modelDao;
    }

    public ManufacturerDao getManufacturerDao() {
        if (this.manufacturerDao == null) {
            if (DAOTYPE.equals("h2")) this.manufacturerDao = new H2ManufacturerDao(this.connection);
        }
        return manufacturerDao;
    }
}
