package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.Model;

public interface ModelDao extends BaseDao<Integer,Model> {
    public Model getByValue(String value) throws DaoException;
}
