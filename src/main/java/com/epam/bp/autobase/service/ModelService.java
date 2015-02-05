package com.epam.bp.autobase.service;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.dao.ModelDao;
import com.epam.bp.autobase.dao.hibernate.Hibernate;
import com.epam.bp.autobase.model.dto.ModelDto;
import com.epam.bp.autobase.model.entity.Model;

import javax.enterprise.event.Event;
import javax.inject.Inject;

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
    public void update(ModelDto dto) throws ServiceException {
        update(dto, dao, event, ss.getLocale());
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
    public String checkFieldsWhileCreate(Model model) throws ServiceException {
        return checkFieldNotBusy(VALUE, model.getValue(), dao, ss.getLocale());
    }

    @Override
    public String checkFieldsWhileUpdate(Model model, ModelDto dto) throws ServiceException {
        return !model.getValue().equals(dto.getValue()) ? checkFieldNotBusy(VALUE, dto.getValue(), dao, ss.getLocale()) : "";
    }
}
