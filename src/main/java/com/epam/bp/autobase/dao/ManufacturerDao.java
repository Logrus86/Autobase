package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.model.entity.Manufacturer;

public interface ManufacturerDao extends BaseDao<Manufacturer>{

    Manufacturer getByValue(String value) throws DaoException;

}
