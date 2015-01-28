package com.epam.bp.autobase.dao.hibernate;

import com.epam.bp.autobase.dao.ColorDao;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.model.entity.Color;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@RequestScoped
public class HibernateColorDao extends AbstractHibernateDao<Color> implements ColorDao {

    @Inject
    EntityManager em;

    public HibernateColorDao() {
        super(Color.class);
        super.setEm(em);
    }

    @Override
    public Color getByValueEn(String valueEn) throws DaoException {
        return getByFieldValue("value_en", valueEn);
    }

    @Override
    public Color getByValueRu(String valueRu) throws DaoException {
        return getByFieldValue("value_ru", valueRu);
    }
}
