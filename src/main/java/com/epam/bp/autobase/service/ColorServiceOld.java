package com.epam.bp.autobase.service;

import com.epam.bp.autobase.model.entity.Color;

import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Stateful
@Model
public class ColorServiceOld {
    @Inject
    EntityManager em;

    @Inject
    private Event<Color> colorEventSrc;

    String MSG = "msg";
    Map<String, String> errorMap;

    Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    Color newEntity;

    public void create() throws ServiceException {
        //remember current JVM locale
        /*Locale oldLocale = Locale.getDefault();
        //change it to current sessionScoped locale to get localized validator & error messages
        Locale.setDefault(us.getLocale());*/
        StringBuilder sb = new StringBuilder();
        //ejb validation
        errorMap = new HashMap<>();
        Set<ConstraintViolation<Color>> cvs = validator().validate(newEntity);
        for (ConstraintViolation<Color> cv : cvs) {
            sb.append(cv.getMessage()).append(": ").append(cv.getInvalidValue()).append("; ");
            errorMap.put(cv.getPropertyPath() + "_" + MSG, cv.getMessage());
        }
        // check busyness of value_en
     /*   if (checkFieldValueExists(VALUE_EN, super.getNewEntity().getValue_en())) {
            String error = ResourceBundle.getBundle(RB).getString("error.busy-color");
            sb.append(error);
            errorMap.put(VALUE_EN + "_" + MSG, error);
        }
        // check busyness of value_ru
        if (checkFieldValueExists(VALUE_RU, super.getNewEntity().getValue_ru())) {
            String error = ResourceBundle.getBundle(RB).getString("error.busy-color");
            sb.append(error);
            errorMap.put(VALUE_RU + "_" + MSG, error);*/

        //}return JVM locale back
      /*  Locale.setDefault(oldLocale);*/
        // if sb is empty all is ok, proceed to persist
        if (sb.toString().isEmpty()) {
            em.persist(newEntity);
            colorEventSrc.fire(newEntity);
            errorMap = null;
            //...or clear color and throw exception
        } else {
            throw new ServiceException("Error while color creation: " + sb.toString());
        }
    }

    public void initNewEntity() {
        newEntity = new Color();
    }
}
