package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.Entity;

import java.util.List;
import java.util.Map;

public interface BaseDao<PK, T extends Entity> {

    public void create(T object) throws DaoException;

    public T getById(PK id) throws DaoException;

    public void update(T object) throws DaoException;

    public void delete(PK id) throws DaoException;

    public void delete(T object) throws DaoException;

    public List<T> getAll() throws DaoException;

    public List<T> getAllSortedBy(String columnName) throws DaoException;

    public List<T> getListByParameter(String param_name, String param_value) throws DaoException;

    public List<T> getListByParametersMap(Map<String, String> params) throws DaoException;

}
