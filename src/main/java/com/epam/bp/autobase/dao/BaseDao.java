package com.epam.bp.autobase.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<PK, T extends Identifiable<PK>> {


    public void create(T object) throws DaoException;

    public T getById(PK id) throws DaoException;

    public void update(T object) throws DaoException;

    public void delete(PK id) throws DaoException;

    public void delete(T object) throws DaoException;

    public List<T> getAll() throws DaoException;

    public List<T> findByParams(Map<String, String> params) throws DaoException;

}
