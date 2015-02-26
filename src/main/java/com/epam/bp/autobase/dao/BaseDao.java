package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.Identifiable;

import java.util.List;

public interface BaseDao<T extends Identifiable> {

    void create(T entity) throws DaoException;

    T getById(Integer id) throws DaoException;

    List<T> getAll() throws DaoException;

    void update(T entity) throws DaoException;

    void delete(Integer id) throws DaoException;

    void delete(T entity) throws DaoException;

    boolean checkValueExists(String fieldName, String value) throws DaoException;

}
