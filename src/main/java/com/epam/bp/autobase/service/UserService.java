package com.epam.bp.autobase.service;

import com.epam.bp.autobase.entity.User;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.Locale;


@Stateful
@Named
@SessionScoped
public class UserService implements Serializable {
    @Inject
    private EntityManager em;
    @Inject
    private Event<User> userEventSrc;
    @Inject
    Logger logger;
    private User sessionUser;
    private Locale locale;

    public Locale getLocale() {
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setLocaleFromLangCode(String lang_code) {
        this.locale = new Locale(lang_code);
    }

    public void setSessionUser(User user) {
        this.sessionUser = user;
    }

    public User getSessionUser() {
        return sessionUser;
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
            em.persist(sessionUser);
            userEventSrc.fire(sessionUser);
        } catch (ConstraintViolationException cve) {
            result.append(cve.getConstraintViolations());
        }
        return result.toString();
    }

    @PostConstruct
    public void initNewUser() {
        sessionUser = new User();
    }

    public void clearSessionUser() {
        sessionUser = null;
    }
}
