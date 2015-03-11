package com.epam.bp.autobase.service;

import com.epam.bp.autobase.dao.ModelDao;
import com.epam.bp.autobase.dao.hibernate.Hibernate;
import com.epam.bp.autobase.ejb.SessionState;
import com.epam.bp.autobase.model.dto.ModelDto;
import com.epam.bp.autobase.model.entity.Model;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;

@javax.enterprise.inject.Model
public class ModelService extends AbstractService<Model, ModelDto, ModelDao> {

    @Inject
    @Hibernate
    ModelDao dao;

    @Inject
    Event<Model> event;

    @Inject
    SessionState ss;

    @Override
    public void create(ModelDto dto) throws ServiceException {
        create(dto, dao, event, ss.getLocale());
    }

    @Override
    public ModelDto getById(Integer id) throws ServiceException {
        return getById(id, dao);
    }

    @Override
    public List<ModelDto> getAll() throws ServiceException {
        return getAll(dao);
    }

    @Override
    public void update(ModelDto dto) throws ServiceException {
        update(dto, dao, event, ss.getLocale());
    }

    @Override
    public void update(Model entity) throws ServiceException {
        update(entity, dao, event);
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        delete(id, dao, event);
    }

    @Override
    public void delete(ModelDto dto) throws ServiceException {
        delete(dto, dao, event);
    }

    @Override
    public ModelDto getDtoFromEntity(Model model) {
        return new ModelDto(model);
    }

    @Override
    public String checkFieldsNotBusyWhileCreate(Model newEntity) throws ServiceException {
        return checkFieldNotBusy(VALUE, newEntity.getValue(), dao, ss.getLocale());
    }

    @Override
    public String checkFieldsNotBusyWhileUpdate(Model oldEntity, ModelDto dtoWithChangedFields) throws ServiceException {
        return !oldEntity.getValue().equals(dtoWithChangedFields.getValue()) ? checkFieldNotBusy(VALUE, dtoWithChangedFields.getValue(), dao, ss.getLocale()) : "";
    }
}
