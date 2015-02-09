package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.model.entity.Model;

public interface ModelDao extends BaseDao<Model> {

    Model getByValue(String value) throws DaoException;

}
