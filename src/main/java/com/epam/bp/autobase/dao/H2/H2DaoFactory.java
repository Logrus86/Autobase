package com.epam.bp.autobase.dao.H2;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.pool.ConnectionPool;

public class H2DaoFactory extends DaoFactory {

    private ConnectionPool.ProxyConnection connection;

    @Override
    public ConnectionPool.ProxyConnection getContext() throws DaoException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (Exception e) {
            throw new DaoException("Error while H2DaoFactory get connection from pool", e);
        }
        return connection;
    }

    @Override
    public void releaseContext() throws DaoException {
        try {
            this.connection.close();
        } catch (Exception e) {
            throw new DaoException("Error while H2DaoFactory release context (return connection to pool)", e);
        }
    }

    public H2DaoManager getDaoManager() {
        return new H2DaoManager(connection);
    }

    public static H2DaoFactory getInstance() throws DaoException {
        try {
            InstanceHolder.instance.getContext();
        } catch (Exception e) {
            throw new DaoException("Error while get H2DaoFactory instance", e);
        }
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static volatile H2DaoFactory instance = new H2DaoFactory();
    }

}
