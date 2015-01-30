package com.epam.bp.autobase.dao.hibernate;

import com.epam.bp.autobase.dao.BaseDao;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.model.entity.Identifiable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractHibernateDao<T extends Identifiable> implements BaseDao<T> {

    private Class<T> entityClass;

    public AbstractHibernateDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected void create(T entity, EntityManager em) throws DaoException {
        try {
            em.persist(entity);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    protected T getById(Integer id, EntityManager em) throws DaoException {
        try {
            return em.find(entityClass, id);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    protected List<T> getAll(EntityManager em) throws DaoException {
        try {
            TypedQuery<T> query = em.createQuery(entityClass.getSimpleName() + ".getAll", entityClass);
            return query.getResultList();
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    protected void update(T entity, EntityManager em) throws DaoException {
        try {
            em.merge(entity);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    protected void delete(Integer id, EntityManager em) throws DaoException {
        try {
            em.remove(em.find(entityClass, id));
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    protected void delete(T entity, EntityManager em) throws DaoException {
        try {
            em.remove(entity);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    public abstract List<T> getListByValue(String field, String value) throws DaoException;

    protected List<T> getListByValue(String field, String value, EntityManager em) throws DaoException {
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

    public abstract T getByFieldValue(String field, String value) throws DaoException;

    protected T getByFieldValue(String field, String value, EntityManager em) throws DaoException {
        try {
            return getListByValue(field, value, em).get(0);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }

    protected boolean checkValueExists(String field, String value, EntityManager em) throws DaoException {
        try {
            return getListByValue(field, value, em) != null;
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }
    }
}
