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
@Transactional
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
        em.persist(entity);
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    @Override
    public Identifiable getById(Integer id) throws DaoException {
        return em.find(entityClass, id);
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    @Override
    public List getAll() throws DaoException {
        TypedQuery<? extends Identifiable> query = em.createQuery(entityClass.getSimpleName() + ".getAll", entityClass);
        return query.getResultList();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public void update(Identifiable entity) throws DaoException {
        em.merge(entity);
    }

    @Override
    public void delete(Integer id) throws DaoException {
        em.remove(em.find(entityClass, id));
    }

    @Override
    public void delete(Identifiable entity) throws DaoException {
        em.remove(entity);
    }

    public List<Identifiable> getListByFieldValue(String field, String value) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Restrictions.eq(field, value));
        if (criteria.list().isEmpty()) return null;
        return criteria.list();
    }

    public Identifiable getByFieldValue(String field, String value) {
        return getListByFieldValue(field, value).get(0);
    }

    public boolean checkFieldValueExists(String field, String value) {
        return getListByFieldValue(field, value).isEmpty();
    }
}
