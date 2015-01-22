package com.epam.bp.autobase.service;

import com.epam.bp.autobase.model.dto.BaseDto;
import com.epam.bp.autobase.model.entity.Identifiable;

import java.io.Serializable;

public interface Service<T extends BaseDto> extends Serializable {

    public void create(T dto) throws ServiceException;

    public T getById(int id) throws ServiceException;

    public void update(T dto) throws ServiceException;

    public void delete(int id) throws ServiceException;

    public void delete(T dto) throws ServiceException;

    public T getDtoFromEntity(Identifiable entity);

    public Identifiable getEntityFromDto(T dto);
}
