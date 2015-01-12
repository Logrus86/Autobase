package com.epam.bp.autobase.dao;

import java.util.ResourceBundle;

public abstract class DaoFactory<Context> {
    private static final ResourceBundle rb = ResourceBundle.getBundle("dao");
    private static final String DAO_TYPE = rb.getString("dao.type");
    private static final String FACTORY_CLASS_PREFIX = "com.epam.bp.autobase.dao.";
    private static final String FACTORY_CLASS_POSTFIX = ".DaoFactory";

    public abstract com.epam.bp.autobase.dao.DaoManager getDaoManager();

    public abstract Context getContext() throws DaoException;

    public abstract void releaseContext() throws DaoException;

    public static DaoFactory getInstance() throws DaoException {
        try {
            InstanceHolder.instance = (DaoFactory) Class.forName(FACTORY_CLASS_PREFIX + DAO_TYPE + FACTORY_CLASS_POSTFIX)
                    .getMethod("getInstance").invoke(null);
        } catch (Exception e) {
            throw new DaoException("Error while get DaoFactory instance", e);
        }
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static volatile DaoFactory instance;
    }
}
