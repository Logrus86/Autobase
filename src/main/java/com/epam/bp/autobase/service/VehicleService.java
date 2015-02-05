package com.epam.bp.autobase.service;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.dao.hibernate.Hibernate;
import com.epam.bp.autobase.model.dto.VehicleDto;
import com.epam.bp.autobase.model.entity.Vehicle;
import org.jboss.logging.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class VehicleService extends AbstractService<Vehicle, VehicleDto, VehicleDao> {

    @Inject
    Logger logger;
    @Inject
    @Hibernate
    private VehicleDao dao;
    @Inject
    private SessionState ss;
    @Inject
    private Event<Vehicle> event;

    @Override
    public void create(VehicleDto dto) throws ServiceException {
        create(dto, dao, event, ss.getLocale());
    }

    @Override
    public VehicleDto getById(Integer id) throws ServiceException {
        return getById(id, dao);
    }

    @Override
    public void update(VehicleDto dto) throws ServiceException {
        update(dto, dao, event, ss.getLocale());
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        delete(id, dao, event);
    }

    @Override
    public void delete(VehicleDto dto) throws ServiceException {
        delete(dto, dao, event);
    }

    @Override
    public VehicleDto getDtoFromEntity(Vehicle entity) {
        return new VehicleDto(entity);
    }

    @Override
    public String checkFieldsWhileCreate(Vehicle identifiable) throws ServiceException {
        return null;
    }

    @Override
    public String checkFieldsWhileUpdate(Vehicle identifiable, VehicleDto dto) throws ServiceException {
        return null;
    }
}
