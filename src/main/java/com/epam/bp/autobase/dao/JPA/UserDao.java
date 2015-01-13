package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.entity.User;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDao extends AbstractDao<Integer, User> implements com.epam.bp.autobase.dao.UserDao {
    public UserDao(EntityManager em) {
        super(em, User.class);
    }

    @Override
    public List<User> getUsersListByUsername(String username) throws DaoException {
        return getListByParameter("username", username);
    }

    @Override
    public User getByCredentials(String username, String password) throws DaoException {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        if ((getListByParametersMap(map).get(0)) != null) {
            return getListByParametersMap(map).get(0);
        } else return null;
    }
}
