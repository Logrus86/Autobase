package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.pool.ConnectionPool;

import java.util.ResourceBundle;

public class DaoManager {
    private ConnectionPool.ProxyConnection connection;
    private static final ResourceBundle RB = ResourceBundle.getBundle("db");
    private static final String DAOTYPE = RB.getString("dao.type");
    private UserDao userDao;
    private VehicleDao vehicleDao;
    private ColorDao colorDao;

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
}
