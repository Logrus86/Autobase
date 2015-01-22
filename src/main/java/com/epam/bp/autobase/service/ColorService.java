package com.epam.bp.autobase.service;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.jpa.HibernateColorDao;
import com.epam.bp.autobase.model.dto.BaseDto;
import com.epam.bp.autobase.model.dto.ColorDto;
import com.epam.bp.autobase.model.entity.Color;
import com.epam.bp.autobase.model.entity.Identifiable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
public class ColorService implements Service {

    @Inject
    private HibernateColorDao dao;

    @Override
    public void create(BaseDto dto) throws ServiceException {
        try {
            dao.create(getEntityFromDto(dto));
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
            dao.update(getEntityFromDto(dto));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            dao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(BaseDto dto) throws ServiceException {
        try {
            dao.delete(getEntityFromDto(dto));
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
}
