package com.epam.bp.autobase.dao.hibernate;

import com.epam.bp.autobase.dao.ColorDao;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.model.entity.Color;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Hibernate
@RequestScoped
public class HibernateColorDao extends AbstractHibernateDao<Color> implements ColorDao {

    @Inject
    EntityManager em;

    public HibernateColorDao() {
        super(Color.class);
    }

    @Override
    @Transactional
    public void create(Color entity) throws DaoException {
        super.create(entity, em);
    }

    @Override
    public Color getById(Integer id) throws DaoException {
        return super.getById(id, em);
    }

    @Override
    public List<Color> getAll() throws DaoException {
        return super.getAll(em);
    }

    @Override
    @Transactional
    public void update(Color entity) throws DaoException {
        super.update(entity, em);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws DaoException {
        super.delete(id, em);
    }

    @Override
    @Transactional
    public void delete(Color entity) throws DaoException {
        super.delete(entity, em);
    }

    @Override
    public List<Color> getListByValue(String field, String value) throws DaoException {
        return super.getListByValue(field, value, em);
    }

    @Override
    public Color getByFieldValue(String field, String value) throws DaoException {
        return super.getByFieldValue(field, value, em);
    }

    @Override
    public boolean checkValueExists(String field, String value) throws DaoException {
        return super.checkValueExists(field, value, em);
    }

    @Override
    public Color getByValueEn(String valueEn) throws DaoException {
        return getByFieldValue("value_en", valueEn);
    }

    @Override
    public Color getByValueRu(String valueRu) throws DaoException {
        return getByFieldValue("value_ru", valueRu);
    }

    @Override
    public List<Color> getAllSortedByEn() throws DaoException {
        TypedQuery<Color> query = em.createNamedQuery("Color.getAllSortedByRu", Color.class);
        return query.getResultList();
    }

    @Override
    public List<Color> getAllSortedByRu() throws DaoException {
        TypedQuery<Color> query = em.createNamedQuery("Color.getAllSortedByEn", Color.class);
        return query.getResultList();
    }
}
