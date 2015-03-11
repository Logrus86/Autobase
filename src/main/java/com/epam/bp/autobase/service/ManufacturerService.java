package com.epam.bp.autobase.service;

import com.epam.bp.autobase.dao.ManufacturerDao;
import com.epam.bp.autobase.dao.hibernate.Hibernate;
import com.epam.bp.autobase.ejb.SessionState;
import com.epam.bp.autobase.model.dto.ManufacturerDto;
import com.epam.bp.autobase.model.entity.Manufacturer;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
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
    public List<ManufacturerDto> getAll() throws ServiceException {
        return getAll(dao);
    }

    @Override
    public void update(ManufacturerDto dto) throws ServiceException {
        update(dto, dao, event, ss.getLocale());
    }

    @Override
    public void update(Manufacturer entity) throws ServiceException {
        update(entity, dao, event);
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
    public String checkFieldsNotBusyWhileCreate(Manufacturer newEntity) throws ServiceException {
        return checkFieldNotBusy(VALUE, newEntity.getValue(), dao, ss.getLocale());
    }

    @Override
    public String checkFieldsNotBusyWhileUpdate(Manufacturer oldEntity, ManufacturerDto dtoWithChangedFields) throws ServiceException {
        return !oldEntity.getValue().equals(dtoWithChangedFields.getValue()) ? checkFieldNotBusy(VALUE, dtoWithChangedFields.getValue(), dao, ss.getLocale()) : "";
    }
}
