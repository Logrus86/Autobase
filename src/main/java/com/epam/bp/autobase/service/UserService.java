package com.epam.bp.autobase.service;

import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.model.entity.Vehicle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
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
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String DOB = "dob";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_REPEAT = "password-repeat";
    private static final String RB = "i18n.text";
    private static final String BALANCE = "balance";
    private static final String ROLE = "role";
    private static final String ID = "id";
    private static final String MSG = "msg";

    @Inject
    Logger logger;
    @Inject
    private EntityManager em;
    @Inject
    private Event<User> userEventSrc;
    private User sessionUser;
    private Locale locale;
    private Map<String, String> errorMap;

    @RequestScoped
    public Map getErrorMap() {
        return errorMap;
    }

    private Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    public Locale getLocale() {
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

    public void create(Map<String, String> userDataMap) throws ServiceException {
        //remember current JVM locale
        Locale oldLocale = Locale.getDefault();
        //change it to current sessionScoped locale to get localized validator & error messages
        Locale.setDefault(getLocale());
        StringBuilder sb = new StringBuilder();
        initNewUser();
        sessionUser.setFirstname(userDataMap.get(FIRSTNAME))
                .setLastname(userDataMap.get(LASTNAME))
                .setDob(userDataMap.get(DOB))
                .setUsername(userDataMap.get(USERNAME))
                .setPassword(userDataMap.get(PASSWORD))
                .setEmail(userDataMap.get(EMAIL))
                .setRole(User.Role.CLIENT)
                .setBalance(BigDecimal.ZERO);
        //ejb validation
        Set<ConstraintViolation<User>> cvs = validator().validate(sessionUser);
        errorMap = userDataMap;
        for (ConstraintViolation<User> cv : cvs) {
            sb.append(cv.getMessage()).append(": ").append(cv.getInvalidValue()).append("; ");
            errorMap.put(cv.getPropertyPath() + "_" + MSG, cv.getMessage());
        }
        //check password and password-repeat are the same
        if (!userDataMap.get(PASSWORD).equals(userDataMap.get(PASSWORD_REPEAT))) {
            String error = ResourceBundle.getBundle(RB).getString("error.passes-not-equals");
            sb.append(error);
            errorMap.put(PASSWORD + "_" + MSG, error);
        }
        // check busyness of username
        if (checkFieldValueExists(USERNAME, sessionUser.getUsername())) {
            String error = ResourceBundle.getBundle(RB).getString("error.busy-username");
            sb.append(error);
            errorMap.put(USERNAME + "_" + MSG, error);
        }
        // check busyness of email
        if (checkFieldValueExists(EMAIL, sessionUser.getEmail())) {
            String error = ResourceBundle.getBundle(RB).getString("error.busy-email");
            sb.append(error);
            errorMap.put(EMAIL + "_" + MSG, error);
        }
        //return JVM locale back
        Locale.setDefault(oldLocale);
        // if sb is empty all is ok, proceed to persist
        if (sb.toString().isEmpty()) {
            em.persist(sessionUser);
            userEventSrc.fire(sessionUser);
            errorMap = null;
            //...or clear user and throw exception
        } else {
            initNewUser();
            throw new ServiceException("Error while user registration: " + sb.toString());
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

    public void update(Map<String, String> userDataMap) throws ServiceException {
        //remember current JVM locale
        Locale oldLocale = Locale.getDefault();
        //change it to current sessionScoped locale to get localized validator & error messages
        Locale.setDefault(getLocale());
        StringBuilder sb = new StringBuilder();
        User user = new User()
                .setFirstname(userDataMap.get(FIRSTNAME))
                .setLastname(userDataMap.get(LASTNAME))
                .setDob(userDataMap.get(DOB))
                .setEmail(userDataMap.get(EMAIL))
                .setUsername(userDataMap.get(USERNAME))
                .setPassword(userDataMap.get(PASSWORD));
        String balance = userDataMap.get(BALANCE);
        if (balance != null) user.setBalance(new BigDecimal(userDataMap.get(BALANCE)));
        String role = userDataMap.get(ROLE);
        if (role != null) user.setRole(role);
        String idString = userDataMap.get(ID);
        if (userDataMap.get(ID) != null) user.setId(Integer.parseInt(idString));
        //ejb validation
        errorMap = new HashMap<>();
        Set<ConstraintViolation<User>> cvs = validator().validate(user);
        for (ConstraintViolation<User> cv : cvs) {
            sb.append(cv.getMessage()).append(": ").append(cv.getInvalidValue()).append("; ");
            errorMap.put(cv.getPropertyPath() + "_" + MSG, cv.getMessage());
            try {
                String propertyValue = String.valueOf(User.class.getMethod("get" + cv.getPropertyPath()).invoke(null));
                errorMap.put(cv.getPropertyPath().toString(), propertyValue);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }
        // if username was changed
        if (!em.find(User.class, user.getId()).getUsername().equals(user.getUsername())) {
            // check busyness of new username
            if (checkFieldValueExists(USERNAME, user.getUsername())) {
                String error = ResourceBundle.getBundle(RB).getString("error.busy-username");
                sb.append(error);
                errorMap.put(USERNAME + "_" + MSG, error);
            }
        }
        // if email was changed
        if (!em.find(User.class, user.getId()).getEmail().equals(user.getEmail())) {
            //check busyness of new email
            if (checkFieldValueExists(EMAIL, user.getEmail())) {
                String error = ResourceBundle.getBundle(RB).getString("error.busy-email");
                sb.append(error);
                errorMap.put(EMAIL + "_" + MSG, error);
            }
        }
        //return JVM locale back
        Locale.setDefault(oldLocale);
        // if sb is empty all is ok, proceed to persist
        if (sb.toString().isEmpty()) {
            em.merge(user);
            userEventSrc.fire(user);
            errorMap = null;
            //...or throw ServiceException
        } else {
            throw new ServiceException("Error while user updating: " + sb.toString());
        }
    }

    public void delete(Integer id) {
        // retrieve user by id
        User userToDelete = em.find(User.class, id);
        // unlink his vehicles if he is driver
        if ((userToDelete.getRole().equals(User.Role.DRIVER)) & (!userToDelete.getVehicles().isEmpty())) {
            for (Vehicle vehicle : userToDelete.getVehicles()) {
                vehicle.setDriver(null);
            }
        }
        em.remove(userToDelete);
        userEventSrc.fire(userToDelete);
    }
}
