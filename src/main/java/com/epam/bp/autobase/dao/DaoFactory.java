package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.pool.ConnectionPool;

import java.sql.SQLException;

public class DaoFactory {
    private static H2UserDao h2UserDao;

    private static DaoFactory instance = new DaoFactory();
    private ConnectionPool.ProxyConnection proxyConnection;

    public static DaoFactory getInstance() {
        instance.getConnection();
        return instance;
    }

    public ConnectionPool.ProxyConnection getConnection() {
        ConnectionPool.ProxyConnection result = null;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            result = cp.getConnection();
        } catch (SQLException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.proxyConnection = result;
        return result;
    }

    private DaoFactory() {

    }

    public H2UserDao getH2UserDao() {
        h2UserDao = new H2UserDao(proxyConnection);
        return h2UserDao;
    }

}
