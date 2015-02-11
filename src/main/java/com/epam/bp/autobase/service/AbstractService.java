package com.epam.bp.autobase.service;

import com.epam.bp.autobase.dao.BaseDao;
import com.epam.bp.autobase.model.dto.AbstractDto;
import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.model.entity.Identifiable;
import com.epam.bp.autobase.util.MyMessageInterpolator;

import javax.enterprise.event.Event;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

public abstract class AbstractService<I extends Identifiable, T extends AbstractDto, M extends BaseDao<I>> {
    public static final String RB = "i18n.text";
    public static final String MSG = "msg";
    public static final String PASSWORD = "password";
    public static final String CREATE_ERR = "create_err";
    public static final String UPDATE_ERR = "update_err";
    public static final String VALUE = "value";
    private static final String PASSES_NOT_EQUALS_ERROR = "error.passes-not-equals";
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
            I entity = (I) dto.buildLazyEntity();
            String errors = validateWhileCreate(entity, locale);
            if (dto.getClass().equals(UserDto.class))
                if (!((UserDto) dto).getPassword().equals(((UserDto) dto).getPassword_repeat())) {
                    String passes_err = ResourceBundle.getBundle(RB, locale).getString(PASSES_NOT_EQUALS_ERROR);
                    errors = errors + passes_err;
                    errorMap.put(PASSWORD + "_" + MSG, passes_err);
                }
            if ("".equals(errors)) {
                dao.create(entity);
                event.fire(entity);
            } else {
                throw new ServiceException(entity.getClass().getSimpleName() + " isn't created due validation error: " + errors);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    public abstract T getById(Integer id) throws ServiceException;

    protected T getById(Integer id, M dao) throws ServiceException {
        try {
            return getDtoFromEntity(dao.getById(id));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    public abstract void update(T dto) throws ServiceException;

    protected void update(T dto, M dao, Event<I> event, Locale locale) throws ServiceException {
        try {
            I entity = dao.getById(dto.getId());
            String errors = validateWhileUpdate(entity, dto, locale);
            if ("".equals(errors)) {
                dao.update((I) dto.overwriteEntityFromDto(entity));
                event.fire(entity);
            } else {
                throw new ServiceException(entity.getClass().getSimpleName() + " isn't updated due validation error: " + errors);
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    public abstract void delete(Integer id) throws ServiceException;

    protected void delete(Integer id, M dao, Event<I> event) throws ServiceException {
        try {
            I entity = dao.getById(id);
            dao.delete(id);
            event.fire(entity);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    public abstract void delete(T dto) throws ServiceException;

    protected void delete(T dto, M dao, Event<I> event) throws ServiceException {
        try {
            I entity = (I) dto.buildLazyEntity();
            dao.delete(entity);
            event.fire(entity);
        } catch (Exception e) {
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

    public String validate0(I identifiable, Locale locale) {
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<I>> cvs = validator(locale).validate(identifiable);
        if (!cvs.isEmpty()) {
            errorMap = new HashMap<>();
            for (ConstraintViolation<I> cv : cvs) {
                if (errorMap.size() > 0) sb.append("; ");
                sb.append(cv.getMessage()).append(": ").append(cv.getInvalidValue());
                errorMap.put(cv.getPropertyPath().toString() + "_" + MSG, cv.getMessage());
                errorMap.put(cv.getPropertyPath().toString(), cv.getInvalidValue().toString());
            }
        }
        
        return sb.toString();
    }

    public String validateWhileCreate(I newEntity, Locale locale) throws ServiceException {
        String one = validate0(newEntity, locale);
        String two = checkFieldsWhileCreate(newEntity);
        String result = (one == null ? "" : one) + (two == null ? "" : two);
        if (!"".equals(result)) {
            if (errorMap == null) errorMap = new HashMap<>();
            errorMap.put(CREATE_ERR, result);
        }
        return result;
    }

    public String validateWhileUpdate(I oldEntity, T dto, Locale locale) throws ServiceException {
        String result = validate0((I) dto.overwriteEntityFromDto(oldEntity), locale) + checkFieldsWhileUpdate(oldEntity, dto);
        if (!"".equals(result)) {
            if (errorMap == null) errorMap = new HashMap<>();
            errorMap.put(UPDATE_ERR, result);
        }
        return result;
    }

    protected String checkFieldNotBusy(String name, String value, M dao, Locale locale) throws ServiceException {
        try {
            if (dao.checkValueExists(name, value)) {
                String error = ResourceBundle.getBundle(RB, locale).getString("error.busy-" + name);
                if (getErrorMap() == null) setErrorMap(new HashMap<>());
                getErrorMap().put(name + "_" + MSG, error);
                getErrorMap().put(name, value);
                return error;
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
        return "";
    }

    public abstract String checkFieldsWhileCreate(I newEntity) throws ServiceException;

    public abstract String checkFieldsWhileUpdate(I oldEntity, T dto) throws ServiceException;

}
