package com.epam.bp.autobase.service;

import com.epam.bp.autobase.model.entity.Identifiable;
import com.epam.bp.autobase.util.MyMessageInterpolator;

import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Stateful
@Model
public class CommonService {
    public static final String RB = "i18n.text";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String DOB = "dob";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_REPEAT = "password-repeat";
    public static final String BALANCE = "balance";
    public static final String ROLE = "role";
    public static final String ID = "id";
    public static final String MSG = "msg";
    public static final String VALUE_EN = "value_en";
    public static final String VALUE_RU = "value_ru";
    private Map<String, String> errorMap = new HashMap<>();

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> map) {
        errorMap = map;
    }

    public Validator validator(Locale locale) {
        ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new MyMessageInterpolator().setLocale(locale))
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    public String validate(Identifiable identifiable, Locale locale) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<Identifiable>> cvs = validator(locale).validate(identifiable);
        errorMap = new HashMap<>();
        for (ConstraintViolation<Identifiable> cv : cvs) {
            sb.append(cv.getMessage()).append(": ").append(cv.getInvalidValue()).append("; ");
            errorMap.put(cv.getPropertyPath() + "_" + MSG, cv.getMessage());
            errorMap.put(cv.getPropertyPath().toString(), cv.getInvalidValue().toString());
        }
        sb.append(checkNecessaryFieldsNotBusy(identifiable));
        return sb.toString();
    }

    public String checkNecessaryFieldsNotBusy(Identifiable identifiable) throws ServiceException {
        // OVERRIDE THIS IN ALL SUBCLASSES!
        return "IF YOU SEE THIS, 'checkNecessaryFieldsNotBusy' METHOD ISN'T OVERRIDDEN IN " + identifiable.getClass().getSimpleName() + "Service !";
    }
}
