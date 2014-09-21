package com.epam.bp.autobase.dao;

import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class DaoFactory<Context> {
    private static final ResourceBundle RB = ResourceBundle.getBundle("db");
    private static final String DAOTYPE = RB.getString("dao.type");

    public static DaoFactory instance;
    public abstract DaoManager getDaoManager();
    public abstract Context getContext() throws DaoException, ClassNotFoundException, InterruptedException;
    public abstract void releaseContext() throws SQLException;
    public static DaoFactory getInstance() throws DaoException, InterruptedException, ClassNotFoundException {
        if (DAOTYPE.equals("h2")) instance = H2DaoFactory.getInstance();
        return instance;
    }
}
