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
    private static final String ERROR_BUSY_USERNAME = RB.getString("error.busy-username");
    private static final String ERROR_BUSY_EMAIL = RB.getString("error.busy-email"); // CREATE !!!!!!!
    
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
        Set<ConstraintViolation<User>> cvs = getValidator().validate(sessionUser);
        errorMap = userDataMap;
        for (ConstraintViolation<User> cv : cvs) {
            sb.append(cv.getMessage()).append(": ").append(cv.getInvalidValue()).append("; ");
            errorMap.put(cv.getPropertyPath() + "_" + RegisterServlet.ATTRIBUTE_ERROR, cv.getMessage());
            errorMap.remove(cv.getPropertyPath().toString());
        }
        if (!userDataMap.get(RegisterServlet.PARAM_PASSWORD).equals(userDataMap.get(RegisterServlet.PARAM_PASSWORD_REPEAT))) {
            sb.append(ERROR_PASSES_NOT_EQUALS);
            errorMap.put(RegisterServlet.PARAM_PASSWORD + "_" + RegisterServlet.ATTRIBUTE_ERROR, ERROR_PASSES_NOT_EQUALS);
        }
        if (checkFieldValueExists(RegisterServlet.PARAM_USERNAME, sessionUser.getUsername())) {
            String s = errorMap.get(RegisterServlet.PARAM_USERNAME + "_" + RegisterServlet.ATTRIBUTE_ERROR);
            if (s != null) {
                sb.append(s).append(", ").append(ERROR_BUSY_USERNAME);
                errorMap.put(RegisterServlet.PARAM_USERNAME + "_" + RegisterServlet.ATTRIBUTE_ERROR, s);
            } else {
                sb.append(ERROR_BUSY_USERNAME);
                errorMap.put(RegisterServlet.PARAM_USERNAME + "_" + RegisterServlet.ATTRIBUTE_ERROR, ERROR_BUSY_USERNAME);
            }
            errorMap.remove(RegisterServlet.PARAM_USERNAME);
        }
        if (checkFieldValueExists(RegisterServlet.PARAM_EMAIL, sessionUser.getEmail())) {
            String s = errorMap.get(RegisterServlet.PARAM_EMAIL + "_" + RegisterServlet.ATTRIBUTE_ERROR);
            if (s != null) {
                sb.append(s).append(", ").append(ERROR_BUSY_EMAIL);
                errorMap.put(RegisterServlet.PARAM_EMAIL + "_" + RegisterServlet.ATTRIBUTE_ERROR, s);
            } else {
                sb.append(ERROR_BUSY_EMAIL);
                errorMap.put(RegisterServlet.PARAM_EMAIL + "_" + RegisterServlet.ATTRIBUTE_ERROR, ERROR_BUSY_EMAIL);
            }
            errorMap.remove(RegisterServlet.PARAM_EMAIL);
        }
        if (sb.toString().isEmpty()) {
            em.persist(sessionUser);
            userEventSrc.fire(sessionUser);
            errorMap = null;
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
