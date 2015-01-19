package com.epam.bp.autobase.service;

import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.servlet.RegisterServlet;
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
import java.math.BigDecimal;
import java.util.*;


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
    private HashMap<String, String> errorMap;

    public Map getErrorMap() {
        return errorMap;
    }

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

    public void register(HashMap<String, String> userDataMap) throws ServiceException {
        initNewUser();
        sessionUser.setFirstname(userDataMap.get(RegisterServlet.PARAM_FIRSTNAME))
                .setLastname(userDataMap.get(RegisterServlet.PARAM_LASTNAME))
                .setDob(userDataMap.get(RegisterServlet.PARAM_DOB))
                .setUsername(userDataMap.get(RegisterServlet.PARAM_USERNAME))
                .setPassword(userDataMap.get(RegisterServlet.PARAM_PASSWORD))
                .setEmail(userDataMap.get(RegisterServlet.PARAM_EMAIL))
                .setRole(User.Role.CLIENT)
                .setBalance(BigDecimal.ZERO);
        Set<ConstraintViolation<User>> cvs = getValidator().validate(sessionUser);
        errorMap = userDataMap;
        for (ConstraintViolation<User> cv : cvs) {
            errorMap.put(cv.getPropertyPath() + "_" + RegisterServlet.ATTRIBUTE_ERROR, cv.getMessage());
            errorMap.remove(cv.getPropertyPath().toString());
        }
        if (!userDataMap.get(RegisterServlet.PARAM_PASSWORD).equals(userDataMap.get(RegisterServlet.PARAM_PASSWORD_REPEAT))) {
            errorMap.put(RegisterServlet.PARAM_PASSWORD + "_" + RegisterServlet.ATTRIBUTE_ERROR, ERROR_PASSES_NOT_EQUALS);
        }
        if (errorMap.isEmpty()) {
            em.persist(sessionUser);
            userEventSrc.fire(sessionUser);
            errorMap = null;
        } else {
            initNewUser();
            throw new ServiceException("Error while user registration due invalid data");
        }
    }

    @PostConstruct
    public void initNewUser() {
        sessionUser = new User();
    }

}
