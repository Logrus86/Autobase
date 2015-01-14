package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;

public class JpaDaoFactory extends DaoFactory {

    @Override
    public JpaDaoManager getDaoManager() {
        return new JpaDaoManager();
    }

    @Override
    public Object getContext() throws DaoException {
        return null;
    }

    @Override
    public void releaseContext() throws DaoException {

    }

    public static JpaDaoFactory getInstance() throws DaoException {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static volatile JpaDaoFactory instance = new JpaDaoFactory();
    }
}
