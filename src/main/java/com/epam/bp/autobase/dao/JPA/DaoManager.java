package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.*;

import javax.persistence.EntityManager;

public class DaoManager implements com.epam.bp.autobase.dao.DaoManager{
    private EntityManager em;
    private UserDao userDao;
    private ColorDao colorDao;

    public DaoManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UserDao(em);
        }
        return userDao;
    }

    @Override
    public VehicleDao getVehicleDao() {
        return null;
    }

    @Override
    public ColorDao getColorDao() {
        if (this.colorDao == null) {
            this.colorDao = new ColorDao(em);
        }
        return colorDao;
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


    @Override
    public void executeAndClose(DaoCommand command) throws DaoException {
        command.execute(this);
    }

    @Override
    public void transaction(DaoCommand command) throws DaoException {
        command.execute(this);
    }

    @Override
    public void transactionAndClose(DaoCommand command) throws DaoException {
        command.execute(this);
    }

}
