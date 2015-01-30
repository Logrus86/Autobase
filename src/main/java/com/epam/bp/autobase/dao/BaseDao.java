package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.model.entity.Identifiable;

import java.util.List;

public interface BaseDao<T extends Identifiable> {

    public void create(T entity) throws DaoException;

    public T getById(Integer id) throws DaoException;

    public List<T> getAll() throws DaoException;

    public void update(T entity) throws DaoException;

    public void delete(Integer id) throws DaoException;

    public void delete(T entity) throws DaoException;

    public boolean checkValueExists(String fieldName, String value) throws DaoException;

}
