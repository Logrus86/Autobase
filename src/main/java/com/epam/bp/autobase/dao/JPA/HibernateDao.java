package com.epam.bp.autobase.dao.jpa;

import com.epam.bp.autobase.dao.Dao;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.model.entity.Identifiable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class HibernateDao implements Dao {

    @Inject
    protected EntityManager em;
    private Class<? extends Identifiable> entityClass;

    public HibernateDao() {
    }

    public HibernateDao(Class<? extends Identifiable> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public void create(Identifiable entity) throws DaoException {
        try {
            em.persist(entity);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    @Override
    public Identifiable getById(Integer id) throws DaoException {
        try {
            return em.find(entityClass, id);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    @Override
    public List getAll() throws DaoException {
        try {
            TypedQuery<? extends Identifiable> query = em.createQuery(entityClass.getSimpleName() + ".getAll", entityClass);
            return query.getResultList();
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public void update(Identifiable entity) throws DaoException {
        try {
            em.merge(entity);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public void delete(Integer id) throws DaoException {
        try {
            em.remove(em.find(entityClass, id));
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public void delete(Identifiable entity) throws DaoException {
        try {
            em.remove(entity);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<Identifiable> getListByFieldValue(String field, String value) throws DaoException {
        try {
            Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.eq(field, value));
            if (criteria.list().isEmpty()) return null;
            return criteria.list();
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    public Identifiable getByFieldValue(String field, String value) throws DaoException {
        try {
            return getListByFieldValue(field, value).get(0);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    public boolean checkFieldValueExists(String field, String value) throws DaoException {
        try {
            return getListByFieldValue(field, value) != null;
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }
}
