package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.DaoException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ResourceBundle;

public class DaoFactory extends com.epam.bp.autobase.dao.DaoFactory  {
    private static final ResourceBundle rb = ResourceBundle.getBundle("dao");
    private static final String DAO_TYPE = rb.getString("dao.type");
    private static final String PERSISTENCE_UNIT_NAME = DAO_TYPE;
    private EntityManagerFactory emf;

    @Override
    public Object getContext() throws DaoException {
            if ((emf == null) || (!emf.isOpen())) {
                emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            }
        return emf;
    }

    @Override
    public void releaseContext() throws DaoException {
        if ((emf != null) && (emf.isOpen())) {
            emf.close();
        }
    }

    @Override
    public DaoManager getDaoManager() {
        return new DaoManager(emf);
    }

    public static com.epam.bp.autobase.dao.DaoFactory getInstance() throws DaoException {
        try {
            InstanceHolder.instance.getContext();
        } catch (Exception e) {
            throw new DaoException("Error while get JPA DaoFactory instance", e);
        }
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static volatile com.epam.bp.autobase.dao.DaoFactory instance = new DaoFactory();
    }
}
