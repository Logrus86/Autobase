package com.epam.bp.autobase.dao.JPA;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JpaUserDao extends JpaAbstractDao<Integer, User> implements UserDao {

    public JpaUserDao() {
        super(User.class);
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
