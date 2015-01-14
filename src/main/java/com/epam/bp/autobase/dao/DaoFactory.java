package com.epam.bp.autobase.dao;

import java.util.ResourceBundle;

public abstract class DaoFactory<Context> {
    private static final ResourceBundle rb = ResourceBundle.getBundle("dao");
    private static final String DAO_TYPE = rb.getString("dao.type");
    private static final String FACTORY_CLASS_PATH = "com.epam.bp.autobase.dao.";
    private static final String FACTORY_CLASS_POSTFIX = "DaoFactory";

    public static DaoFactory getInstance() throws DaoException {
        try {
            String daoClassPrefix = "." + DAO_TYPE.substring(0, 1).toUpperCase() + DAO_TYPE.substring(1).toLowerCase();
            InstanceHolder.instance = (DaoFactory) Class.forName(FACTORY_CLASS_PATH + DAO_TYPE + daoClassPrefix + FACTORY_CLASS_POSTFIX)
                    .getMethod("getInstance").invoke(null);
        } catch (Exception e) {
            throw new DaoException("Error while get H2DaoFactory instance", e);
        }
        return InstanceHolder.instance;
    }

    public abstract com.epam.bp.autobase.dao.DaoManager getDaoManager();

    public abstract Context getContext() throws DaoException;

    public abstract void releaseContext() throws DaoException;

    private static class InstanceHolder {
        private static volatile DaoFactory instance;
    }
}
