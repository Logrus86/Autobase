package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.pool.ConnectionPool;

import java.sql.SQLException;

public class H2DaoFactory extends DaoFactory<ConnectionPool.ProxyConnection> {

    private ConnectionPool.ProxyConnection connection;

    @Override
    public ConnectionPool.ProxyConnection getContext() throws DaoException {
        ConnectionPool.ProxyConnection result;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            result = cp.getConnection();
        } catch (Exception e) {
            throw new DaoException("DaoFactory error while get connection from pool.", e);
        }
        this.connection = result;
        return result;
    }

    @Override
    public void releaseContext() throws SQLException {
        this.connection.close();
    }

    public DaoManager getDaoManager() {
        return new DaoManager(connection);
    }

    public static DaoFactory getInstance() throws DaoException, InterruptedException, ClassNotFoundException {
        InstanceHolder.instance.getContext();
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static volatile DaoFactory instance = new H2DaoFactory();
    }

}
