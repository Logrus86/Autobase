package com.epam.bp.autobase.service;

import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.servlet.RegisterServlet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Stateful
@Named
@SessionScoped
public class UserService implements Serializable {
    private static final String RB = "i18n.text";
    @Inject
    Logger logger;
    @Inject
    private EntityManager em;
    @Inject
    private Event<User> userEventSrc;

    private User sessionUser;
    private Locale locale;
    private HashMap<String, String> errorMap;

    public Map getErrorMap() {
        return errorMap;
    }

    public void clearErrorMap() {
        this.errorMap = null;
    }

    private Validator getValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
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
        TypedQuery<User> query = em.createNamedQuery("User.findByCredentials", User.class)
                .setParameter("username", username)
                .setParameter("password", password);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void register(HashMap<String, String> userDataMap) throws ServiceException {
        Locale oldLocale = Locale.getDefault();
        Locale.setDefault(getLocale());
        StringBuilder sb = new StringBuilder();
        initNewUser();
        sessionUser.setFirstname(userDataMap.get(RegisterServlet.PARAM_FIRSTNAME))
                .setLastname(userDataMap.get(RegisterServlet.PARAM_LASTNAME))
                .setDob(userDataMap.get(RegisterServlet.PARAM_DOB))
                .setUsername(userDataMap.get(RegisterServlet.PARAM_USERNAME))
                .setPassword(userDataMap.get(RegisterServlet.PARAM_PASSWORD))
                .setEmail(userDataMap.get(RegisterServlet.PARAM_EMAIL))
                .setRole(User.Role.CLIENT)
                .setBalance(BigDecimal.ZERO);
        //ejb validation
        Set<ConstraintViolation<User>> cvs = getValidator().validate(sessionUser);
        errorMap = userDataMap;
        for (ConstraintViolation<User> cv : cvs) {
            sb.append(cv.getMessage()).append(": ").append(cv.getInvalidValue()).append("; ");
            errorMap.put(cv.getPropertyPath() + "_" + RegisterServlet.ATTRIBUTE_ERROR, cv.getMessage());
            errorMap.remove(cv.getPropertyPath().toString());
        }
        //check password and password-repeat are the same
        if (!userDataMap.get(RegisterServlet.PARAM_PASSWORD).equals(userDataMap.get(RegisterServlet.PARAM_PASSWORD_REPEAT))) {
            String error = ResourceBundle.getBundle(RB).getString("error.passes-not-equals");
            sb.append(error);
            errorMap.put(RegisterServlet.PARAM_PASSWORD + "_" + RegisterServlet.ATTRIBUTE_ERROR, error);
        }
        // check busyness of username
        if (checkFieldValueExists(RegisterServlet.PARAM_USERNAME, sessionUser.getUsername())) {
            String error = ResourceBundle.getBundle(RB).getString("error.busy-username");
            sb.append(error);
            errorMap.put(RegisterServlet.PARAM_USERNAME + "_" + RegisterServlet.ATTRIBUTE_ERROR, error);
            errorMap.remove(RegisterServlet.PARAM_USERNAME);
        }
        // check busyness of email
        if (checkFieldValueExists(RegisterServlet.PARAM_EMAIL, sessionUser.getEmail())) {
            String error = ResourceBundle.getBundle(RB).getString("error.busy-email");
            sb.append(error);
            errorMap.put(RegisterServlet.PARAM_EMAIL + "_" + RegisterServlet.ATTRIBUTE_ERROR, error);
            errorMap.remove(RegisterServlet.PARAM_EMAIL);
        }
        // if sb is empty all is ok, proceed to persist
        Locale.setDefault(oldLocale);
        if (sb.toString().isEmpty()) {
            em.persist(sessionUser);
            userEventSrc.fire(sessionUser);
            errorMap = null;
            //...or clear user and throw exception
        } else {
            initNewUser();
            throw new ServiceException("Error while user registration due invalid data: " + sb.toString());
        }
    }

    @PostConstruct
    public void initNewUser() {
        sessionUser = new User();
    }

    public boolean checkFieldValueExists(String field, String value) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq(field, value));
        return !criteria.list().isEmpty();
    }
}
