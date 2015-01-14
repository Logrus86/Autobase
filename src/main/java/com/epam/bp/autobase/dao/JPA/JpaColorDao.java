package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.ColorDao;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.entity.Color;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


public class JpaColorDao extends JpaAbstractDao<Integer, Color> implements ColorDao {

    public JpaColorDao() {
        super(Color.class);
    }

    @Override
    public Color getByValueEn(String value_en) throws DaoException {
        return getListByParameter("value_en", value_en).get(0);
    }

    @Override
    public Color getByValueRu(String value_ru) throws DaoException {
        return getListByParameter("value_ru", value_ru).get(0);
    }
}
