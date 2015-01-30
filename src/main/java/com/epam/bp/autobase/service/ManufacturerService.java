package com.epam.bp.autobase.service;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.dao.ManufacturerDao;
import com.epam.bp.autobase.dao.hibernate.Hibernate;
import com.epam.bp.autobase.model.dto.ManufacturerDto;
import com.epam.bp.autobase.model.entity.Manufacturer;

import javax.enterprise.event.Event;
import javax.inject.Inject;

@javax.enterprise.inject.Model
public class ManufacturerService extends AbstractService<Manufacturer, ManufacturerDto, ManufacturerDao> {

    @Inject
    @Hibernate
    ManufacturerDao dao;

    @Inject
    Event<Manufacturer> event;

    @Inject
    SessionState ss;

    @Override
    public void create(ManufacturerDto dto) throws ServiceException {
        create(dto, dao, event, ss.getLocale());
    }

    @Override
    public ManufacturerDto getById(Integer id) throws ServiceException {
        return getById(id, dao);
    }

    @Override
    public void update(ManufacturerDto dto) throws ServiceException {
        update(dto, dao, event, ss.getLocale());
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        delete(id, dao, event);
    }

    @Override
    public void delete(ManufacturerDto dto) throws ServiceException {
        delete(dto, dao, event);
    }

    @Override
    public ManufacturerDto getDtoFromEntity(Manufacturer manufacturer) {
        return new ManufacturerDto(manufacturer);
    }

    @Override
    public String checkAllFieldsNotBusy(Manufacturer manufacturer) throws ServiceException {
        return checkFieldNotBusy(VALUE, manufacturer.getValue(), dao, ss.getLocale());
    }

    @Override
    public String checkChangedFieldsNotBusy(Manufacturer manufacturer, ManufacturerDto dto) throws ServiceException {
        return !manufacturer.getValue().equals(dto.getValue()) ? checkFieldNotBusy(VALUE, dto.getValue(), dao, ss.getLocale()) : null;
    }
}
