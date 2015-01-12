package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.BaseDao;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.Identifiable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public abstract class AbstractDao<PK extends Integer, T extends Identifiable> implements BaseDao<PK, T> {
    private final EntityManagerFactory EMF;
    private final Class<T> ENTITY_CLASS;

    public AbstractDao(EntityManagerFactory emf, Class<T> entityClass) {
        this.EMF = emf;
        this.ENTITY_CLASS = entityClass;
    }

    @Override
    public void create(T entity) throws DaoException {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(entity);
        et.commit();
        em.close();
    }

    @Override
    public T getById(PK id) throws DaoException {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        T entity = em.find(ENTITY_CLASS, id);
        et.commit();
        em.close();
        return entity;
    }

    @Override
    public void update(T entity) throws DaoException {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.refresh(entity);
        et.commit();
        em.close();
    }

    @Override
    public void delete(PK id) throws DaoException {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(em.find(ENTITY_CLASS, id));
        et.commit();
        em.close();
    }

    @Override
    public void delete(T entity) throws DaoException {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(entity);
        et.commit();
        em.close();
    }

    @Override
    public List<T> getAll() throws DaoException {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        TypedQuery<T> query = em.createNamedQuery("SELECT e FROM " + ENTITY_CLASS.getName() + " e ORDER_BY e.ID;", ENTITY_CLASS);
        List<T> result = query.getResultList();
        et.commit();
        em.close();
        return result;
    }

    @Override
    public List<T> getAllSortedBy(String columnName) throws DaoException {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        TypedQuery<T> query = em.createNamedQuery("SELECT e FROM " + ENTITY_CLASS.getName() + " e ORDER_BY e." + columnName + ";", ENTITY_CLASS);
        List<T> result = query.getResultList();
        et.commit();
        em.close();
        return result;
    }

    @Override
    public List<T> getListByParameter(String param_name, String param_value) throws DaoException {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        TypedQuery<T> query = em.createNamedQuery("SELECT e FROM " + ENTITY_CLASS.getName() + " e WHERE e." + param_name + " = :param_value ORDER_BY e.ID;", ENTITY_CLASS);
        query.setParameter("param_value", param_value);
        List<T> result = query.getResultList();
        et.commit();
        em.close();
        return result;
    }

    @Override
    public List<T> getListByParametersMap(Map<String, String> params) throws DaoException {
        EntityManager em = EMF.createEntityManager();
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
        List<T> result = criteria.list();
        em.close();
        return result;
    }
}
