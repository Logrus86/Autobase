package com.epam.bp.autobase.dao.H2;

import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.pool.ConnectionPool;

public class DaoManager implements com.epam.bp.autobase.dao.DaoManager {
    protected ConnectionPool.ProxyConnection connection = null;
    protected com.epam.bp.autobase.dao.UserDao userDao = null;
    protected com.epam.bp.autobase.dao.VehicleDao vehicleDao = null;
    protected com.epam.bp.autobase.dao.ColorDao colorDao = null;
    protected com.epam.bp.autobase.dao.ModelDao modelDao = null;
    protected com.epam.bp.autobase.dao.ManufacturerDao manufacturerDao = null;

    public DaoManager(ConnectionPool.ProxyConnection connection) {
        this.connection = connection;
    }

    @Override
    public com.epam.bp.autobase.dao.UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UserDao(this.connection);
        }
        return userDao;
    }

    @Override
    public com.epam.bp.autobase.dao.VehicleDao getVehicleDao() {
        if (this.vehicleDao == null) {
            this.vehicleDao = new VehicleDao(this.connection);
        }
        return vehicleDao;
    }

    @Override
    public com.epam.bp.autobase.dao.ColorDao getColorDao() {
        if (this.colorDao == null) {
            this.colorDao = new ColorDao(this.connection);
        }
        return colorDao;
    }

    @Override
    public com.epam.bp.autobase.dao.ModelDao getModelDao() {
        if (this.modelDao == null) {
            this.modelDao = new ModelDao(this.connection);
        }
        return modelDao;
    }

    @Override
    public com.epam.bp.autobase.dao.ManufacturerDao getManufacturerDao() {
        if (this.manufacturerDao == null) {
            this.manufacturerDao = new ManufacturerDao(this.connection);
        }
        return manufacturerDao;
    }

    public interface DaoCommand {
        public void execute(DaoManager daoManager) throws DaoException;
    }

    @SuppressWarnings("ThrowFromFinallyBlock")
    public void executeAndClose(DaoCommand command) throws DaoException {
        try {
            command.execute(this);
        } catch (Exception e) {
            throw new DaoException("Error while executing command", e);
        } finally {
            try {
                this.connection.close();
            } catch (Exception e) {
                throw new DaoException("Error while closing connection", e);
            }
        }
    }

    @SuppressWarnings("ThrowFromFinallyBlock")
    public void transaction(DaoCommand command) throws DaoException {
        try {
            this.connection.setAutoCommit(false);
            command.execute(this);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
            } catch (Exception e1) {
                throw new DaoException("Error while rollback transaction", e);
            }
            throw new DaoException("Error while executing transaction ", e);
        } finally {
            try {
                this.connection.setAutoCommit(true);
            } catch (Exception e) {
                throw new DaoException("Error while setAutoCommit true", e);
            }
        }
    }

    public void transactionAndClose(DaoCommand command) throws DaoException {
        executeAndClose(daoManager -> daoManager.transaction(command));
    }
}
