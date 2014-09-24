package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.props.Manufacturer;

public interface ManufacturerDao extends BaseDao<Integer,Manufacturer> {
    public Manufacturer getByValue(String value) throws DaoException;
}
