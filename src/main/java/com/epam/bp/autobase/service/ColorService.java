package com.epam.bp.autobase.service;

import com.epam.bp.autobase.entity.Color;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

@Stateful
@Model
public class ColorService {
    private static final String VALUE_EN = "value_en";
    private static final String VALUE_RU = "value_ru";
    private static final String RB = "i18n.text";
    private static final String ID = "id";
    private static final String MSG = "msg";
    @Inject
    Logger logger;
    @Inject
    private EntityManager em;
    @Inject
    private Event<Color> colorEventSrc;
    @Inject
    UserService us;
    private Map<String, String> errorMap;

    public Map getErrorMap() {
        return errorMap;
    }

    private Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    public void create(String value_en, String value_ru) throws ServiceException {
        //remember current JVM locale
        Locale oldLocale = Locale.getDefault();
        //change it to current sessionScoped locale to get localized validator & error messages
        Locale.setDefault(us.getLocale());
        StringBuilder sb = new StringBuilder();
        Color color = new Color()
                .setValue_en(value_en)
                .setValue_ru(value_ru);

        //ejb validation
        errorMap = new HashMap<>();
        Set<ConstraintViolation<Color>> cvs = validator().validate(color);
        for (ConstraintViolation<Color> cv : cvs) {
            sb.append(cv.getMessage()).append(": ").append(cv.getInvalidValue()).append("; ");
            errorMap.put(cv.getPropertyPath() + "_" + MSG, cv.getMessage());
        }
        // check busyness of value_en
        if (checkFieldValueExists(VALUE_EN, color.getValue_en())) {
            String error = ResourceBundle.getBundle(RB).getString("error.busy-color");
            sb.append(error);
            errorMap.put(VALUE_EN + "_" + MSG, error);
        }
        // check busyness of value_ru
        if (checkFieldValueExists(VALUE_RU, color.getValue_ru())) {
            String error = ResourceBundle.getBundle(RB).getString("error.busy-color");
            sb.append(error);
            errorMap.put(VALUE_RU + "_" + MSG, error);
        }
        //return JVM locale back
        Locale.setDefault(oldLocale);
        // if sb is empty all is ok, proceed to persist
        if (sb.toString().isEmpty()) {
            em.persist(color);
            colorEventSrc.fire(color);
            errorMap = null;
            //...or clear color and throw exception
        } else {
            throw new ServiceException("Error while color creation: " + sb.toString());
        }
    }

    public boolean checkFieldValueExists(String field, String value) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Color.class);
        criteria.add(Restrictions.eq(field, value));
        return !criteria.list().isEmpty();
    }

    public void update(Integer id, String valueEn, String valueRu) throws ServiceException {
        //remember current JVM locale
        Locale oldLocale = Locale.getDefault();
        //change it to current sessionScoped locale to get localized validator & error messages
        Locale.setDefault(us.getLocale());
        StringBuilder sb = new StringBuilder();
        Color color = new Color()
                .setValue_en(valueEn)
                .setValue_ru(valueRu);
        color.setId(id);
        //ejb validation
        errorMap = new HashMap<>();
        Set<ConstraintViolation<Color>> cvs = validator().validate(color);
        for (ConstraintViolation<Color> cv : cvs) {
            sb.append(cv.getMessage()).append(": ").append(cv.getInvalidValue()).append("; ");
            errorMap.put(cv.getPropertyPath() + "_" + MSG, cv.getMessage());
        }
        // if value_en was changed
        if (!em.find(Color.class, color.getId()).getValue_en().equals(color.getValue_en())) {
            // check busyness of new value_en
            if (checkFieldValueExists(VALUE_EN, color.getValue_en())) {
                String error = ResourceBundle.getBundle(RB).getString("error.busy-color");
                sb.append(error);
                errorMap.put(VALUE_EN + "_" + MSG, error);
            }
        }
        // if value_ru was changed
        if (!em.find(Color.class, color.getId()).getValue_ru().equals(color.getValue_ru())) {
            // check busyness of new value_ru
            if (checkFieldValueExists(VALUE_RU, color.getValue_ru())) {
                String error = ResourceBundle.getBundle(RB).getString("error.busy-color");
                sb.append(error);
                errorMap.put(VALUE_RU + "_" + MSG, error);
            }
        }
        //return JVM locale back
        Locale.setDefault(oldLocale);
        // if sb is empty all is ok, proceed to persist
        if (sb.toString().isEmpty()) {
            em.merge(color);
            colorEventSrc.fire(color);
            errorMap = null;
            //...or clear color and throw exception
        } else {
            throw new ServiceException("Error while color updating: " + sb.toString());
        }
    }

    public void delete(Integer id) {
        // retrieve user by id
        Color colorToDelete = em.find(Color.class, id);
        em.remove(colorToDelete);
        colorEventSrc.fire(colorToDelete);
    }
}
