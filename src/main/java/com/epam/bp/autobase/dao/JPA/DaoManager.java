package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.*;

import javax.persistence.EntityManagerFactory;

public class DaoManager implements com.epam.bp.autobase.dao.DaoManager{
    private EntityManagerFactory emf;
    private UserDao userDao;

    public DaoManager(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UserDao(emf);
        }
        return userDao;
    }

    @Override
    public VehicleDao getVehicleDao() {
        return null;
    }

    @Override
    public ColorDao getColorDao() {
        return null;
    }

    @Override
    public ModelDao getModelDao() {
        return null;
    }

    @Override
    public ManufacturerDao getManufacturerDao() {
        return null;
    }

    @Override
    public OrderDao getOrderDao() {
        return null;
    }

}
