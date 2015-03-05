package com.epam.bp.autobase.dao.hibernate;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.ManufacturerDao;
import com.epam.bp.autobase.model.entity.Manufacturer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


@Hibernate
@RequestScoped
public class HibernateManufacturerDao extends AbstractHibernateDao<Manufacturer> implements ManufacturerDao {
    @Inject
    EntityManager em;

    public HibernateManufacturerDao() {
        super(Manufacturer.class);
    }

    @Transactional
    @Override
    public void create(Manufacturer entity) throws DaoException {
        super.create(entity, em);
    }

    @Override
    public Manufacturer getById(Integer id) throws DaoException {
        return super.getById(id, em);
    }

    @Override
    public List<Manufacturer> getAll() throws DaoException {
        return super.getAll(em);
    }

    @Transactional
    @Override
    public void update(Manufacturer entity) throws DaoException {
        super.update(entity, em);
    }

    @Transactional
    @Override
    public void delete(Integer id) throws DaoException {
        super.delete(id, em);
    }

    @Transactional
    @Override
    public void delete(Manufacturer entity) throws DaoException {
        super.delete(entity, em);
    }

    @Override
    public boolean checkValueExists(String fieldName, String value) throws DaoException {
        return super.checkValueExists(fieldName, value, em);
    }

    @Override
    public List<Manufacturer> getListByValue(String field, String value) throws DaoException {
        return super.getListByValue(field, value, em);
    }

    @Override
    public Manufacturer getByFieldValue(String field, String value) throws DaoException {
        return super.getByFieldValue(field, value, em);
    }

    @Override
    public Manufacturer getByValue(String value) throws DaoException {
        return super.getByFieldValue("value", value, em);
    }
}
