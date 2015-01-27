package com.epam.bp.autobase.service;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.JPA.HibernateColorDao;
import com.epam.bp.autobase.model.dto.BaseDto;
import com.epam.bp.autobase.model.dto.ColorDto;
import com.epam.bp.autobase.model.entity.Color;
import com.epam.bp.autobase.model.entity.Identifiable;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Locale;
import java.util.ResourceBundle;

@Model
public class ColorService extends CommonService implements Service {

    @Inject
    UserService us;
    @Inject
    private HibernateColorDao dao;
    @Inject
    private Event<Color> event;

    @Override
    public void create(BaseDto dto) throws ServiceException {
        try {
            Color color = (Color) getEntityFromDto(dto);
            String errors = validate(color, us.getLocale());
            if ("".equals(errors)) {
                dao.create(color);
                event.fire(color);
            } else {
                throw new ServiceException("Color isn't created due validation error: " + errors);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public BaseDto getById(int id) throws ServiceException {
        try {
            return getDtoFromEntity(dao.getById(id));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void update(BaseDto dto) throws ServiceException {
        try {
            Color color = (Color) getEntityFromDto(dto);
            String errors = validate(color, dto, us.getLocale());
            if ("".equals(errors)) {
                dao.update(color);
                event.fire(color);
            } else {
                throw new ServiceException("Color isn't updated due validation error: " + errors);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            Color color = (Color) dao.getById(id);
            dao.delete(id);
            event.fire(color);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(BaseDto dto) throws ServiceException {
        try {
            Color color = (Color) getEntityFromDto(dto);
            dao.delete(color);
            event.fire(color);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public BaseDto getDtoFromEntity(Identifiable entity) {
        Color color = (Color) entity;
        ColorDto colorDto = new ColorDto()
                .setValue_en(color.getValue_en())
                .setValue_ru(color.getValue_ru());
        if (color.getId() != null) {
            colorDto.setId(color.getId());
        }
        return colorDto;
    }

    @Override
    public Identifiable getEntityFromDto(BaseDto dto) {
        ColorDto colorDto = (ColorDto) dto;
        Color color = new Color()
                .setValue_en(colorDto.getValue_en())
                .setValue_ru(colorDto.getValue_ru());
        if (colorDto.getId() != null) {
            color.setId(colorDto.getId());
        }
        return color;
    }

    @Override
    public String checkAllFieldsNotBusy(Identifiable identifiable) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        Color color = (Color) identifiable;
        Locale locale = us.getLocale();
        try {
            // check busyness of value_en
            if (dao.checkFieldValueExists(VALUE_EN, color.getValue_en())) {
                String error = ResourceBundle.getBundle(RB, locale).getString("error.busy-color");
                sb.append(error);
                getErrorMap().put(VALUE_EN + "_" + MSG, error);
            }
            // check busyness of value_ru
            if (dao.checkFieldValueExists(VALUE_RU, color.getValue_ru())) {
                if (!"".equals(sb.toString())) {
                    sb.append("; ");
                }
                String error = ResourceBundle.getBundle(RB, locale).getString("error.busy-color");
                sb.append(error);
                getErrorMap().put(VALUE_RU + "_" + MSG, error);
            }
            return sb.toString();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public String checkChangedFieldsNotBusy(Identifiable identifiable, BaseDto dto) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        Color color = (Color) identifiable;
        ColorDto colorDto = (ColorDto) dto;
        Locale locale = us.getLocale();
        try {
            // check busyness of value_en if its changed
            if ((!color.getValue_en().equals(colorDto.getValue_en())) && (dao.checkFieldValueExists(VALUE_EN, color.getValue_en()))) {
                String error = ResourceBundle.getBundle(RB, locale).getString("error.busy-color");
                sb.append(error);
                getErrorMap().put(VALUE_EN + "_" + MSG, error);
            }
            // check busyness of value_ru if its changed
            if ((!color.getValue_ru().equals(colorDto.getValue_ru())) && (dao.checkFieldValueExists(VALUE_RU, color.getValue_ru()))) {
                if (!"".equals(sb.toString())) {
                    sb.append("; ");
                }
                String error = ResourceBundle.getBundle(RB, locale).getString("error.busy-color");
                sb.append(error);
                getErrorMap().put(VALUE_RU + "_" + MSG, error);
            }
            return sb.toString();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
