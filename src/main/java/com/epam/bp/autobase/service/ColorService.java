package com.epam.bp.autobase.service;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.dao.ColorDao;
import com.epam.bp.autobase.dao.hibernate.Hibernate;
import com.epam.bp.autobase.model.dto.ColorDto;
import com.epam.bp.autobase.model.entity.Color;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Locale;

@Model
public class ColorService extends AbstractService<Color, ColorDto, ColorDao> {

    private static final String VALUE_EN = "value_en";
    private static final String VALUE_RU = "value_ru";
    @Inject
    SessionState ss;
    @Inject
    @Hibernate
    private ColorDao dao;
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
    public void update(Color entity) throws ServiceException {
        update(entity, dao, event);
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
    public String checkFieldsNotBusyWhileCreate(Color newEntity) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        Locale locale = ss.getLocale();
        sb.append(checkFieldNotBusy(VALUE_EN, newEntity.getValue_en(), dao, locale));
        if (sb.length() != 0) sb.append("; ");
        sb.append(checkFieldNotBusy(VALUE_RU, newEntity.getValue_ru(), dao, locale));
        return sb.toString();
    }

    @Override
    public String checkFieldsNotBusyWhileUpdate(Color oldEntity, ColorDto dtoWithChangedFields) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        Locale locale = ss.getLocale();
        if (!oldEntity.getValue_en().equals(dtoWithChangedFields.getValue_en()))
            sb.append(checkFieldNotBusy(VALUE_EN, dtoWithChangedFields.getValue_en(), dao, locale));
        if (sb.length() != 0) sb.append("; ");
        if (!oldEntity.getValue_ru().equals(dtoWithChangedFields.getValue_ru()))
            sb.append(checkFieldNotBusy(VALUE_RU, dtoWithChangedFields.getValue_ru(), dao, locale));
        return sb.toString();
    }
}
