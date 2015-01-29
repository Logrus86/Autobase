package com.epam.bp.autobase.service;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.dao.ColorDao;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.hibernate.HibernateColorDao;
import com.epam.bp.autobase.model.dto.ColorDto;
import com.epam.bp.autobase.model.entity.Color;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

@Model
public class ColorService extends AbstractService<Color, ColorDto, ColorDao> {

    @Inject
    SessionState ss;
    @Inject
    private HibernateColorDao dao;
    @Inject
    private Event<Color> event;

    @Override
    public void create(ColorDto dto) throws ServiceException {
        create(dto, dao, event, ss.getLocale());
    }

    @Override
    public ColorDto getById(Integer id) throws ServiceException {
        return getById(id, dao);
    }

    @Override
    public void update(ColorDto dto) throws ServiceException {
        update(dto, dao, event, ss.getLocale());
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        delete(id, dao, event);
    }

    @Override
    public void delete(ColorDto dto) throws ServiceException {
        delete(dto, dao, event);
    }

    @Override
    public ColorDto getDtoFromEntity(Color color) {
        return new ColorDto(color);
    }

    @Override
    public String checkAllFieldsNotBusy(Color color) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        Locale locale = ss.getLocale();
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
    public String checkChangedFieldsNotBusy(Color color, ColorDto dto) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        Locale locale = ss.getLocale();
        try {
            // check busyness of value_en if its changed
            if ((!color.getValue_en().equals(dto.getValue_en())) && (dao.checkFieldValueExists(VALUE_EN, color.getValue_en()))) {
                String error = ResourceBundle.getBundle(RB, locale).getString("error.busy-color");
                sb.append(error);
                if (getErrorMap() == null) {
                    setErrorMap(new HashMap<>());
                }
                getErrorMap().put(VALUE_EN + "_" + MSG, error);
            }
            // check busyness of value_ru if its changed
            if ((!color.getValue_ru().equals(dto.getValue_ru())) && (dao.checkFieldValueExists(VALUE_RU, color.getValue_ru()))) {
                if (!"".equals(sb.toString())) {
                    sb.append("; ");
                }
                String error = ResourceBundle.getBundle(RB, locale).getString("error.busy-color");
                sb.append(error);
                if (getErrorMap() == null) {
                    setErrorMap(new HashMap<>());
                }
                getErrorMap().put(VALUE_RU + "_" + MSG, error);
            }
            return sb.toString();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
