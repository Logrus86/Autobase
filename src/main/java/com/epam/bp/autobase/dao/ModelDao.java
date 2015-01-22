package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.model.entity.Model;

public interface ModelDao {
    public Model getByValue(String value) throws DaoException;
}
