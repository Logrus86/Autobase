package com.epam.bp.autobase.dao.jpa;

import com.epam.bp.autobase.dao.ColorDao;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.model.entity.Color;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

@RequestScoped
@Transactional
public class HibernateColorDao extends HibernateDao implements ColorDao {

    public HibernateColorDao() {
        super(Color.class);
    }

    @Override
    public Color getByValueEn(String valueEn) throws DaoException {
        return (Color) getByFieldValue("value_en", valueEn);
    }

    @Override
    public Color getByValueRu(String valueRu) throws DaoException {
        return (Color) getByFieldValue("value_ru", valueRu);
    }
}
