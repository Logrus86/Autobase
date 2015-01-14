package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


public class JpaDaoManager implements DaoManager{
    private JpaUserDao userDao;
    private JpaColorDao colorDao;

    public JpaDaoManager() {
    }

    @Override
    public JpaUserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new JpaUserDao();
        }
        return userDao;
    }

    @Override
    public VehicleDao getVehicleDao() {
        return null;
    }

    @Override
    public JpaColorDao getColorDao() {
        if (this.colorDao == null) {
            this.colorDao = new JpaColorDao();
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
