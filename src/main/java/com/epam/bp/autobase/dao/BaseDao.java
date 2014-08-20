package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.Vehicle;

import java.util.List;

public interface BaseDao<PK, T extends Vehicle> {
    public T getById(PK id);

    public List<T> getAll();

    public boolean create(T entity);

    public T update(T entity);

    public boolean delete(PK id);

    public boolean delete(T entity);

}
