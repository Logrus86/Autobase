package com.epam.bp.autobase.service;

import com.epam.bp.autobase.model.dto.BaseDto;
import com.epam.bp.autobase.model.entity.Identifiable;
import com.epam.bp.autobase.util.MyMessageInterpolator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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
    public static final String CREATE_ERR = "create_err";
    public static final String UPDATE_ERR = "update_err";
    public static final String VALUE_EN = "value_en";
    public static final String VALUE_RU = "value_ru";
    private Map<String, String> errorMap;

    public Map getErrorMap() {
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

    private StringBuilder validate0(Identifiable identifiable, Locale locale) {
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<Identifiable>> cvs = validator(locale).validate(identifiable);
        if (!cvs.isEmpty()) {
            errorMap = new HashMap<>();
            for (ConstraintViolation<Identifiable> cv : cvs) {
                sb.append(cv.getMessage()).append(": ").append(cv.getInvalidValue()).append("; ");
                errorMap.put(cv.getPropertyPath() + "_" + MSG, cv.getMessage());
                errorMap.put(cv.getPropertyPath().toString(), cv.getInvalidValue().toString());
            }
        }
        return sb;
    }

    //for create method
    public String validate(Identifiable identifiable, Locale locale) throws ServiceException {
        String result = validate0(identifiable, locale).append(checkAllFieldsNotBusy(identifiable)).toString();
        errorMap.put(CREATE_ERR, result);
        return result;
    }

    //for update method
    public String validate(Identifiable identifiable, BaseDto dto, Locale locale) throws ServiceException {
        String result = validate0(identifiable, locale).append(checkChangedFieldsNotBusy(identifiable, dto)).toString();
        errorMap.put(UPDATE_ERR, result);
        return result;
    }

    public String checkAllFieldsNotBusy(Identifiable identifiable) throws ServiceException {
        // OVERRIDE THIS IN ALL SUBCLASSES!
        return "IF YOU SEE THIS, 'checkAllFieldsNotBusy' METHOD ISN'T OVERRIDDEN IN " + identifiable.getClass().getSimpleName() + "Service !";
    }

    public String checkChangedFieldsNotBusy(Identifiable identifiable, BaseDto dto) throws ServiceException {
        // OVERRIDE THIS IN ALL SUBCLASSES!
        return "IF YOU SEE THIS, 'checkChangedFieldsNotBusy' METHOD ISN'T OVERRIDDEN IN " + identifiable.getClass().getSimpleName() + "Service !";
    }
}
