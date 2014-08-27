package com.epam.bp.autobase.dao;

import java.util.List;

public interface BaseDao<PK, T extends Identifiable<PK>> {


    public void create(T object);

    public T getById(PK id);

    public void update(T object);

    public void delete(PK id);

    public void delete(T object);

    public List<T> getAll();

}
