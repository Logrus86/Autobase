package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.entity.Color;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

public class ColorDao extends com.epam.bp.autobase.dao.JPA.AbstractDao<Integer, Color> implements com.epam.bp.autobase.dao.ColorDao {

    public ColorDao(EntityManager em) {
        super(em, Color.class);
    }

    @Override
    public Color getByValueEn(String valueEn) throws DaoException {
        return getListByParameter("valueEn", valueEn).get(0);
    }

    @Override
    public Color getByValueRu(String valueRu) throws DaoException {
        return getListByParameter("valueRu", valueRu).get(0);
    }
}
