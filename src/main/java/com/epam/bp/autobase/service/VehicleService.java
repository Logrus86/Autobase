package com.epam.bp.autobase.service;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.dao.hibernate.Hibernate;
import com.epam.bp.autobase.ejb.SessionState;
import com.epam.bp.autobase.model.dto.VehicleDto;
import com.epam.bp.autobase.model.entity.Vehicle;
import org.jboss.logging.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<VehicleDto> getAll() throws ServiceException {
        return getAll(dao);
    }

    @Override
    public void update(VehicleDto dto) throws ServiceException {
        update(dto, dao, event, ss.getLocale());
    }

    @Override
    public void update(Vehicle entity) throws ServiceException {
        update(entity, dao, event);
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
    public String checkFieldsNotBusyWhileCreate(Vehicle newEntity) throws ServiceException {
        return "";
    }

    @Override
    public String checkFieldsNotBusyWhileUpdate(Vehicle oldEntity, VehicleDto dtoWithChangedFields) throws ServiceException {
        return "";
    }

    public List<VehicleDto> getAllBuses() throws ServiceException{
        try {
            return dao.getAllBuses().stream().map(this::getDtoFromEntity).collect(Collectors.toCollection(LinkedList::new));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    public List<VehicleDto> getAllCars() throws ServiceException{
        try {
            return dao.getAllCars().stream().map(this::getDtoFromEntity).collect(Collectors.toCollection(LinkedList::new));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    public List<VehicleDto> getAllTrucks() throws ServiceException{
        try {
            return dao.getAllTrucks().stream().map(this::getDtoFromEntity).collect(Collectors.toCollection(LinkedList::new));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
