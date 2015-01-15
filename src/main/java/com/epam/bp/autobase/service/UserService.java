package com.epam.bp.autobase.service;

import com.epam.bp.autobase.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;
import java.util.logging.Logger;

@Stateful
@Model
public class UserService {
    @Inject
    private Logger logger;
    @Inject
    private EntityManager em;
    @Inject
    private Event<User> userEventSrc;
    private User newUser;

    @Produces
    @Named
    public User getNewUser() {
        return newUser;
    }

    public User findByCredentials(String username, String password) {
        TypedQuery<User> query = em.createNamedQuery("findByCredentials", User.class)
                .setParameter("username", username)
                .setParameter("password", password);
        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    public String register() throws Exception {
        StringBuilder result = new StringBuilder();
        try {
            em.persist(newUser);
            userEventSrc.fire(newUser);
            logger.info("Newly registered user: " + newUser.toString());
        } catch (ConstraintViolationException cve) {
            result.append(cve.getConstraintViolations());
        }
        return result.toString();
    }

    @PostConstruct
    public void initNewUser() {
        newUser = new User();
    }
}
