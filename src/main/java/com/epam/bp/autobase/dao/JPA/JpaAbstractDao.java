package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.BaseDao;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.Color;
import com.epam.bp.autobase.entity.Identifiable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Stateless
public abstract class JpaAbstractDao<PK extends Integer, T extends Identifiable> implements BaseDao<PK, T> {

    @PersistenceContext
    private EntityManager em;
    private final Class<T> ENTITY_CLASS;

    public JpaAbstractDao(Class<T> entityClass) {
        this.ENTITY_CLASS = entityClass;
    }

    @Override
    public void create(T entity) throws DaoException {
        em.persist(entity);
    }

    @Override
    public T getById(PK id) throws DaoException {
        return em.find(ENTITY_CLASS, id);
    }

    @Override
    public void update(T entity) throws DaoException {
        em.refresh(entity);
    }

    @Override
    public void delete(PK id) throws DaoException {
        em.remove(em.find(ENTITY_CLASS, id));
    }

    @Override
    public void delete(T entity) throws DaoException {
        em.remove(entity);
    }

    @Override
    public List<T> getAll() throws DaoException {
          TypedQuery<T> query = em.createQuery("SELECT e FROM " + ENTITY_CLASS.getName() + " e ORDER BY e.id", ENTITY_CLASS);
        return query.getResultList();
    }

    @Override
    public List<T> getAllSortedBy(String columnName) throws DaoException {
        TypedQuery<T> query = em.createQuery("SELECT e FROM " + ENTITY_CLASS.getName() + " e ORDER BY e." + columnName.toLowerCase() + ", e.id DESC", ENTITY_CLASS);
        return query.getResultList();
    }

    @Override
    public List<T> getListByParameter(String param_name, String param_value) throws DaoException {
        TypedQuery<T> query = em.createQuery("SELECT e FROM " + ENTITY_CLASS.getName() + " e WHERE e." + param_name + " = :param_value ORDER_BY e.id;", ENTITY_CLASS);
        query.setParameter("param_value", param_value);
        return query.getResultList();
    }

    @Override
    public List<T> getListByParametersMap(Map<String, String> params) throws DaoException {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(ENTITY_CLASS);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if ((VehicleDao.RENT.equals(entry.getKey())) || (VehicleDao.MILEAGE.equals(entry.getKey()))) {
                criteria.add(Restrictions.lt(entry.getKey(), entry.getValue()));
            } else {
                if ((VehicleDao.PROD_YEAR.equals(entry.getKey())) ||
                        (VehicleDao.PAYLOAD.equals(entry.getKey())) ||
                        (VehicleDao.PASS_PL_NUM.equals(entry.getKey())) ||
                        (VehicleDao.STAND_PL_NUM.equals(entry.getKey()))
                        ) {
                    criteria.add(Restrictions.gt(entry.getKey(), entry.getValue()));
                } else criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
            }
        }
        return criteria.list();
    }
}
