package com.epam.bp.autobase.dao.H2;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoManager;
import com.epam.bp.autobase.pool.ConnectionPool;

public class H2DaoManager implements DaoManager {
    private ConnectionPool.ProxyConnection connection = null;
    private H2UserDao userDao = null;
    private H2VehicleDao vehicleDao = null;
    private H2ColorDao colorDao = null;
    private H2ModelDao modelDao = null;
    private H2ManufacturerDao manufacturerDao = null;
    private H2OrderDao orderDao = null;

    public H2DaoManager(ConnectionPool.ProxyConnection connection) {
        this.connection = connection;
    }

    @Override
    public H2UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new H2UserDao(this.connection);
        }
        return userDao;
    }

    @Override
    public H2VehicleDao getVehicleDao() {
        if (this.vehicleDao == null) {
            this.vehicleDao = new H2VehicleDao(this.connection);
        }
        return vehicleDao;
    }

    @Override
    public H2ColorDao getColorDao() {
        if (this.colorDao == null) {
            this.colorDao = new H2ColorDao(this.connection);
        }
        return colorDao;
    }

    @Override
    public H2ModelDao getModelDao() {
        if (this.modelDao == null) {
            this.modelDao = new H2ModelDao(this.connection);
        }
        return modelDao;
    }

    @Override
    public H2ManufacturerDao getManufacturerDao() {
        if (this.manufacturerDao == null) {
            this.manufacturerDao = new H2ManufacturerDao(this.connection);
        }
        return manufacturerDao;
    }

    @Override
    public H2OrderDao getOrderDao() {
        if (this.orderDao == null) {
            this.orderDao = new H2OrderDao(this.connection);
        }
        return orderDao;
    }

    @Override
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

    @Override
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

    @Override
    public void transactionAndClose(DaoCommand command) throws DaoException {
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
                this.connection.close();
            } catch (Exception e) {
                throw new DaoException("Error while setAutoCommit true", e);
            }
        }
        this.connection.close();
    }
}
