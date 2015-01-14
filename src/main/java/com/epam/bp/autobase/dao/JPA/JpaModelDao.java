package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.ModelDao;
import com.epam.bp.autobase.entity.Model;

public class JpaModelDao extends JpaAbstractDao<Integer, Model> implements ModelDao{

    public JpaModelDao() {
        super(Model.class);
    }

    @Override
    public Model getByValue(String value) throws DaoException {
        return getListByParameter("value", value).get(0);
    }
}
