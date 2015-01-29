package com.epam.bp.autobase.service;

import com.epam.bp.autobase.dao.BaseDao;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.model.dto.AbstractDto;
import com.epam.bp.autobase.model.entity.Identifiable;
import com.epam.bp.autobase.util.MyMessageInterpolator;

import javax.enterprise.event.Event;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public abstract class AbstractService<I extends Identifiable, T extends AbstractDto, M extends BaseDao<I>> {
    public static final String RB = "i18n.text";
    public static final String ID = "id";
    public static final String MSG = "msg";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String DOB = "dob";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_REPEAT = "password-repeat";
    public static final String BALANCE = "balance";
    public static final String ROLE = "role";
    public static final String CREATE_ERR = "create_err";
    public static final String UPDATE_ERR = "update_err";
    public static final String VALUE_EN = "value_en";
    public static final String VALUE_RU = "value_ru";
    private Map<String, String> errorMap;

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> map) {
        errorMap = map;
    }


    public abstract void create(T dto) throws ServiceException;

    protected void create(T dto, M dao, Event<I> event, Locale locale) throws ServiceException {
        try {
            I entity = (I) dto.buildEntity();
            String errors = validateWhileCreate(entity, locale);
            if ("".equals(errors)) {
                dao.create(entity);
                event.fire(entity);
            } else {
                throw new ServiceException(entity.getClass().getSimpleName() + " isn't created due validation error: " + errors);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    public abstract T getById(Integer id) throws ServiceException;

    protected T getById(Integer id, M dao) throws ServiceException {
        try {
            return getDtoFromEntity(dao.getById(id));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    public abstract void update(T dto) throws ServiceException;

    protected void update(T dto, M dao, Event<I> event, Locale locale) throws ServiceException {
        try {
            I entity = (I) dto.buildEntity();
            String errors = validateWhileUpdate(entity, dto, locale);
            if ("".equals(errors)) {
                dao.update(entity);
                event.fire(entity);
            } else {
                throw new ServiceException(entity.getClass().getSimpleName() + " isn't updated due validation error: " + errors);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }

    }

    public abstract void delete(Integer id) throws ServiceException;

    protected void delete(Integer id, M dao, Event<I> event) throws ServiceException {
        try {
            I entity = dao.getById(id);
            dao.delete(id);
            event.fire(entity);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    public abstract void delete(T dto) throws ServiceException;

    protected void delete(T dto, M dao, Event<I> event) throws ServiceException {
        try {
            I entity = (I) dto.buildEntity();
            dao.delete(entity);
            event.fire(entity);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    public abstract T getDtoFromEntity(I entity);

    private Validator validator(Locale locale) {
        ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new MyMessageInterpolator().setLocale(locale))
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    private StringBuilder validate0(I identifiable, Locale locale) {
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<I>> cvs = validator(locale).validate(identifiable);
        if (!cvs.isEmpty()) {
            errorMap = new HashMap<>();
            for (ConstraintViolation<I> cv : cvs) {
                sb.append(cv.getMessage()).append(": ").append(cv.getInvalidValue()).append("; ");
                errorMap.put(cv.getPropertyPath() + "_" + MSG, cv.getMessage());
                errorMap.put(cv.getPropertyPath().toString(), cv.getInvalidValue().toString());
            }
        }
        return sb;
    }

    public String validateWhileCreate(I identifiable, Locale locale) throws ServiceException {
        String result = validate0(identifiable, locale).append(checkAllFieldsNotBusy(identifiable)).toString();
        if (!"".equals(result)) {
            errorMap.put(CREATE_ERR, result);
        }
        return result;
    }

    public String validateWhileUpdate(I identifiable, T dto, Locale locale) throws ServiceException {
        String result = validate0(identifiable, locale).append(checkChangedFieldsNotBusy(identifiable, dto)).toString();
        if (!"".equals(result)) {
            errorMap.put(UPDATE_ERR, result);
        }
        return result;
    }

    public abstract String checkAllFieldsNotBusy(I identifiable) throws ServiceException;

    public abstract String checkChangedFieldsNotBusy(I identifiable, T dto) throws ServiceException;
}
