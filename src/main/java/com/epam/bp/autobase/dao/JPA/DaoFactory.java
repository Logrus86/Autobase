package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.DaoException;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateful
public class DaoFactory extends com.epam.bp.autobase.dao.DaoFactory {


    private EntityManager em;

    public DaoFactory() {
    }

    @Override
    public EntityManager getContext() throws DaoException {
        return em;
    }

    @Override
    public void releaseContext() throws DaoException {
        if ((em != null) && (em.isOpen())) {
            em.close();
        }
    }

    @Override
    public DaoManager getDaoManager() {
        return new DaoManager(em);
    }

    public static DaoFactory getInstance() throws DaoException {
        try {
            InstanceHolder.instance.getContext();
        } catch (Exception e) {
            throw new DaoException("Error while get JPA DaoFactory instance", e);
        }
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static volatile DaoFactory instance = new DaoFactory();
    }
}
