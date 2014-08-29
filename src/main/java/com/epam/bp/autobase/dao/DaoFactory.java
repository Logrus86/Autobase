package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.pool.ConnectionPool;

public class DaoFactory {

    private static DaoFactory instance = new DaoFactory();
    private ConnectionPool.ProxyConnection proxyConnection;

    public static DaoFactory getInstance() throws DaoException {
        instance.getConnection();
        return instance;
    }

    public ConnectionPool.ProxyConnection getConnection() throws DaoException {
        ConnectionPool.ProxyConnection result = null;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            result = cp.getConnection();
        } catch (Exception e) {
            throw new DaoException(e.getCause());
        }
        this.proxyConnection = result;
        return result;
    }

    private DaoFactory() {

    }

    public H2UserDao getH2UserDao() {
        H2UserDao h2UserDao = new H2UserDao(proxyConnection);
        return h2UserDao;
    }

}
