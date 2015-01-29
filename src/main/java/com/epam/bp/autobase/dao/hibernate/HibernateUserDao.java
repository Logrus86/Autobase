package com.epam.bp.autobase.dao.hibernate;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.model.entity.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class HibernateUserDao extends AbstractHibernateDao<User> implements UserDao {

    @Inject
    EntityManager em;

    public HibernateUserDao() {
        super(User.class);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void create(User entity) throws DaoException {
        super.create(entity, em);
    }

    @Override
    public User getById(Integer id) throws DaoException {
        return super.getById(id, em);
    }

    @Override
    public List<User> getAll() throws DaoException {
        return super.getAll(em);
    }

    @Override
    @Transactional
    public void update(User entity) throws DaoException {
        super.update(entity, em);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws DaoException {
        super.delete(id, em);
    }

    @Override
    @Transactional
    public void delete(User entity) throws DaoException {
        super.delete(entity, em);
    }

    @Override
    public List<User> getListByFieldValue(String field, String value) throws DaoException {
        return super.getListByFieldValue(field, value, em);
    }

    @Override
    public User getByFieldValue(String field, String value) throws DaoException {
        return super.getByFieldValue(field, value, em);
    }

    @Override
    public boolean checkFieldValueExists(String field, String value) throws DaoException {
        return super.checkFieldValueExists(field, value, em);
    }

    @Override
    public User findByCredentials(String username, String password) {
        TypedQuery<User> query = em.createNamedQuery("User.findByCredentials", User.class)
                .setParameter("username", username)
                .setParameter("password", password);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
