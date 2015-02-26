package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.Manufacturer;

public interface ManufacturerDao extends BaseDao<Manufacturer>{

    Manufacturer getByValue(String value) throws DaoException;

}
