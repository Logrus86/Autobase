package com.epam.bp.autobase.dao.hibernate;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.ModelDao;
import com.epam.bp.autobase.model.entity.Model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Hibernate
@RequestScoped
public class HibernateModelDao extends AbstractHibernateDao<Model> implements ModelDao {
    @Inject
    EntityManager em;

    public HibernateModelDao() {
        super(Model.class);
    }

    @Transactional
    @Override
    public void create(Model entity) throws DaoException {
        super.create(entity, em);
    }

    @Override
    public Model getById(Integer id) throws DaoException {
        return super.getById(id, em);
    }

    @Override
    public List<Model> getAll() throws DaoException {
        return super.getAll(em);
    }

    @Transactional
    @Override
    public void update(Model entity) throws DaoException {
        super.update(entity, em);
    }

    @Transactional
    @Override
    public void delete(Integer id) throws DaoException {
        super.delete(id, em);
    }

    @Transactional
    @Override
    public void delete(Model entity) throws DaoException {
        super.delete(entity, em);
    }

    @Override
    public boolean checkValueExists(String fieldName, String value) throws DaoException {
        return super.checkValueExists(fieldName, value, em);
    }

    @Override
    public List<Model> getListByValue(String field, String value) throws DaoException {
        return super.getListByValue(field, value, em);
    }

    @Override
    public Model getByFieldValue(String field, String value) throws DaoException {
        return super.getByFieldValue(field, value, em);
    }

    @Override
    public Model getByValue(String value) throws DaoException {
        return super.getByFieldValue("value", value, em);
    }
}
