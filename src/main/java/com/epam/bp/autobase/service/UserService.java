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
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;


@Stateful
@Named
@SessionScoped
public class UserService implements Serializable {
    private static final ResourceBundle RB = ResourceBundle.getBundle("i18n.text");
    private static final String ERROR_PASSES_NOT_EQUALS = RB.getString("error.passes-not-equals");
    @Inject
    Logger logger;
    @Inject
    private EntityManager em;
    @Inject
    private Event<User> userEventSrc;

    private ValidatorFactory vf;
    private User sessionUser;
    private Locale locale;

    private Validator getValidator() {
        if (vf == null) {
            vf = Validation.buildDefaultValidatorFactory();
        }
        return vf.getValidator();
    }
    
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

    public User getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(User user) {
        this.sessionUser = user;
    }

    public User findByCredentials(String username, String password) {
        TypedQuery<User> query = em.createNamedQuery("findByCredentials", User.class)
                .setParameter("username", username)
                .setParameter("password", password);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void register(String firstName, String lastName, String dob, String username, String password, String passwordRepeat, String email) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        initNewUser();
        sessionUser.setFirstname(firstName).setLastname(lastName).setDob(dob).setUsername(username).setPassword(password).setEmail(email);
        Set<ConstraintViolation<User>> cvs = getValidator().validate(sessionUser);
        for (ConstraintViolation<User> cv : cvs) {
            sb.append(cv.getMessage()).append(", ");
        }
        if (!password.equals(passwordRepeat)) {
            sb.append(ERROR_PASSES_NOT_EQUALS);
        }
        if (sb.toString().equals("")) {
            em.persist(sessionUser);
            userEventSrc.fire(sessionUser);
        } else {
            initNewUser();
            throw new ServiceException(sb.toString());
        }
    }

    @PostConstruct
    public void initNewUser() {
        sessionUser = new User();
    }

}
