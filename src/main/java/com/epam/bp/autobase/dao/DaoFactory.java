package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.dao.H2.H2DaoManager;

import java.util.ResourceBundle;

public abstract class DaoFactory<Context> {
    private static final String FACTORY_CLASS = ResourceBundle.getBundle("db").getString("dao.factoryClassName");
    public static DaoFactory instance;

    public abstract H2DaoManager getDaoManager();

    public abstract Context getContext() throws DaoException;

    public abstract void releaseContext() throws DaoException;

    public static DaoFactory getInstance() throws DaoException {
        try {
            instance = (DaoFactory) Class.forName(FACTORY_CLASS).getMethod("getInstance").invoke(null);
        } catch (Exception e) {
            throw new DaoException("Error while get DaoFactory instance", e);
        }
        return instance;
    }
}
