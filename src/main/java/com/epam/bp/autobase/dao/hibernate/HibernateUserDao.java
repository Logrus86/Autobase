package com.epam.bp.autobase.dao.hibernate;

import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.model.entity.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@RequestScoped
public class HibernateUserDao extends AbstractHibernateDao<User> implements UserDao {

    @Inject
    EntityManager em;

    public HibernateUserDao() {
        super(User.class);
        super.setEm(em);
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
