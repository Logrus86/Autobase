package com.epam.bp.autobase.service;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.dao.ColorDao;
import com.epam.bp.autobase.dao.hibernate.HibernateColorDao;
import com.epam.bp.autobase.model.dto.ColorDto;
import com.epam.bp.autobase.model.entity.Color;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Locale;

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
        sb.append(checkFieldNotBusy(VALUE_EN, color.getValue_en(), dao, locale));
        if (!sb.toString().isEmpty()) sb.append("; ");
        sb.append(checkFieldNotBusy(VALUE_RU, color.getValue_ru(), dao, locale));
        return sb.toString();
    }

    @Override
    public String checkChangedFieldsNotBusy(Color color, ColorDto dto) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        Locale locale = ss.getLocale();
        if (!color.getValue_en().equals(dto.getValue_en()))
            sb.append(checkFieldNotBusy(VALUE_EN, dto.getValue_en(), dao, locale));
        if (!sb.toString().isEmpty()) sb.append("; ");
        if (!color.getValue_ru().equals(dto.getValue_ru()))
            sb.append(checkFieldNotBusy(VALUE_RU, dto.getValue_ru(), dao, locale));
        return sb.toString();
    }
}
