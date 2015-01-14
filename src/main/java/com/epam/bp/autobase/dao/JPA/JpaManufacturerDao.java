package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.ManufacturerDao;
import com.epam.bp.autobase.entity.Manufacturer;

public class JpaManufacturerDao extends JpaAbstractDao<Integer, Manufacturer> implements ManufacturerDao {
    public JpaManufacturerDao() {
        super(Manufacturer.class);
    }

    @Override
    public Manufacturer getByValue(String value) throws DaoException {
        return getListByParameter("value", value).get(0);
    }
}
