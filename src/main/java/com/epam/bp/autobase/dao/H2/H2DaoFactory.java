package com.epam.bp.autobase.dao.H2;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.DaoManager;
import com.epam.bp.autobase.pool.ConnectionPool;

public class H2DaoFactory extends DaoFactory<ConnectionPool.ProxyConnection> {

    private ConnectionPool.ProxyConnection connection;

    @Override
    public ConnectionPool.ProxyConnection getContext() throws DaoException {
        ConnectionPool.ProxyConnection result;
        try {
            result = ConnectionPool.getInstance().getConnection();
        } catch (Exception e) {
            throw new DaoException("Error while H2DaoFactory get connection from pool.", e);
        }
        this.connection = result;
        return result;
    }

    @Override
    public void releaseContext() throws DaoException {
        try {
            this.connection.close();
        } catch (Exception e) {
            throw new DaoException("Error while H2DaoFactory release context (return connection to pool)", e);
        }
    }

    public DaoManager getDaoManager() {
        return new DaoManager(connection);
    }

    public static DaoFactory getInstance() throws DaoException {
        try {
            InstanceHolder.instance.getContext();
        } catch (Exception e) {
            throw new DaoException("Error while get H2DaoFactory instance", e);
        }
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static volatile DaoFactory instance = new H2DaoFactory();
    }

}
