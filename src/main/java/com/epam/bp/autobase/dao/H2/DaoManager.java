package com.epam.bp.autobase.dao.H2;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.pool.ConnectionPool;

public class DaoManager implements com.epam.bp.autobase.dao.DaoManager {
    private ConnectionPool.ProxyConnection connection = null;
    private UserDao userDao = null;
    private VehicleDao vehicleDao = null;
    private ColorDao colorDao = null;
    private ModelDao modelDao = null;
    private ManufacturerDao manufacturerDao = null;
    private OrderDao orderDao = null;

    public DaoManager(ConnectionPool.ProxyConnection connection) {
        this.connection = connection;
    }

    @Override
    public UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UserDao(this.connection);
        }
        return userDao;
    }

    @Override
    public VehicleDao getVehicleDao() {
        if (this.vehicleDao == null) {
            this.vehicleDao = new VehicleDao(this.connection);
        }
        return vehicleDao;
    }

    @Override
    public ColorDao getColorDao() {
        if (this.colorDao == null) {
            this.colorDao = new ColorDao(this.connection);
        }
        return colorDao;
    }

    @Override
    public ModelDao getModelDao() {
        if (this.modelDao == null) {
            this.modelDao = new ModelDao(this.connection);
        }
        return modelDao;
    }

    @Override
    public ManufacturerDao getManufacturerDao() {
        if (this.manufacturerDao == null) {
            this.manufacturerDao = new ManufacturerDao(this.connection);
        }
        return manufacturerDao;
    }

    @Override
    public OrderDao getOrderDao() {
        if (this.orderDao == null) {
            this.orderDao = new OrderDao(this.connection);
        }
        return orderDao;
    }

    public interface DaoCommand {
        public void execute(DaoManager daoManager) throws DaoException;
    }

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
