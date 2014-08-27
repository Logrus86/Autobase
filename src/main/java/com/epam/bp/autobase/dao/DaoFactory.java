package com.epam.bp.autobase.dao;

import java.sql.SQLException;

public class DaoFactory {
    private static H2UserDao h2UserDao;

    private static DaoFactory instance = new DaoFactory();
    private ConnectionPool.ProxyConnection proxyConnection;

    public static DaoFactory getInstance() {
        instance.getContext();
        return instance;
    }

    public ConnectionPool.ProxyConnection getContext() {
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

    public void releaseContext() {
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.returnConnection(proxyConnection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private DaoFactory() {

    }

    public H2UserDao getH2UserDao() {
        h2UserDao = new H2UserDao(proxyConnection);
        return h2UserDao;
    }

}
