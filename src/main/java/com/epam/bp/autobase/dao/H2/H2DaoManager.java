package com.epam.bp.autobase.dao.H2;

import com.epam.bp.autobase.dao.*;
import com.epam.bp.autobase.pool.ConnectionPool;

public class H2DaoManager {
    protected ConnectionPool.ProxyConnection connection = null;
    protected UserDao userDao = null;
    protected VehicleDao vehicleDao = null;
    protected ColorDao colorDao = null;
    protected ModelDao modelDao = null;
    protected ManufacturerDao manufacturerDao = null;

    public H2DaoManager(ConnectionPool.ProxyConnection connection) {
        this.connection = connection;
    }

    public UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new H2UserDao(this.connection);
        }
        return userDao;
    }

    public VehicleDao getVehicleDao() {
        if (this.vehicleDao == null) {
            this.vehicleDao = new H2VehicleDao(this.connection);
        }
        return vehicleDao;
    }

    public ColorDao getColorDao() {
        if (this.colorDao == null) {
            this.colorDao = new H2ColorDao(this.connection);
        }
        return colorDao;
    }

    public ModelDao getModelDao() {
        if (this.modelDao == null) {
            this.modelDao = new H2ModelDao(this.connection);
        }
        return modelDao;
    }

    public ManufacturerDao getManufacturerDao() {
        if (this.manufacturerDao == null) {
            this.manufacturerDao = new H2ManufacturerDao(this.connection);
        }
        return manufacturerDao;
    }

    public interface DaoCommand {
        public void execute(H2DaoManager daoManager) throws DaoException;
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
        executeAndClose(new DaoCommand() {
            public void execute(H2DaoManager daoManager) throws DaoException {
                daoManager.transaction(command);
            }
        });
    }
}
