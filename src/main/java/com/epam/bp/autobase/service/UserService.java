package com.epam.bp.autobase.service;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.dao.hibernate.HibernateUserDao;
import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.model.entity.Vehicle;
import org.jboss.logging.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

@Model
public class UserService extends AbstractService<User, UserDto, UserDao> {

    @Inject
    Logger logger;
    @Inject
    private HibernateUserDao dao;
    @Inject
    private SessionState ss;
    @Inject
    private Event<User> event;

    @Override
    public void create(UserDto dto) throws ServiceException {
        create(dto, dao, event, ss.getLocale());
    }

    @Override
    public UserDto getById(Integer id) throws ServiceException {
        return getById(id, dao);
    }

    @Override
    public void update(UserDto dto) throws ServiceException {
        if (dto.getRole().equals(User.Role.DRIVER)) unLinkVehicles(dto.buildEntity());
        update(dto, dao, event, ss.getLocale());
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            User user = dao.getById(id);
            unLinkVehicles(user);
        } catch (DaoException e) {
            throw new ServiceException("User with id=" + id + " wasn't found. Abort.");
        }

        delete(id, dao, event);
    }

    @Override
    public void delete(UserDto dto) throws ServiceException {
        User user = dto.buildEntity();
        unLinkVehicles(user);
        delete(dto, dao, event);
    }

    private void unLinkVehicles(User user) {
        if ((user.getRole().equals(User.Role.DRIVER)) & (!user.getVehicles().isEmpty()))
            for (Vehicle vehicle : user.getVehicles()) vehicle.setDriver(null);
    }

    @Override
    public UserDto getDtoFromEntity(User user) {
        return new UserDto(user);
    }

    @Override
    public String checkAllFieldsNotBusy(User user) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        Locale locale = ss.getLocale();
        try {
            if (dao.checkFieldValueExists(USERNAME, user.getUsername())) {
                String error = ResourceBundle.getBundle(RB, locale).getString("error.busy-username");
                sb.append(error);
                logger.info(getErrorMap());
                if (getErrorMap() == null) setErrorMap(new HashMap<>());
                getErrorMap().put(USERNAME + "_" + MSG, error);
                getErrorMap().put(USERNAME, user.getUsername());
            }
            if (dao.checkFieldValueExists(EMAIL, user.getEmail())) {
                if (!"".equals(sb.toString())) sb.append("; ");
                String error = ResourceBundle.getBundle(RB, locale).getString("error.busy-email");
                sb.append(error);
                logger.info(getErrorMap());
                if (getErrorMap() == null) setErrorMap(new HashMap<>());
                getErrorMap().put(EMAIL + "_" + MSG, error);
                getErrorMap().put(EMAIL, user.getEmail());
            }
            return sb.toString();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public String checkChangedFieldsNotBusy(User user, UserDto dto) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        Locale locale = ss.getLocale();
        try {
            if ((!user.getUsername().equals(dto.getUsername())) && (dao.checkFieldValueExists(USERNAME, user.getUsername()))) {
                String error = ResourceBundle.getBundle(RB, locale).getString("error.busy-username");
                sb.append(error);
                if (getErrorMap() == null) setErrorMap(new HashMap<>());
                getErrorMap().put(USERNAME + "_" + MSG, error);
                getErrorMap().put(USERNAME, user.getUsername());
            }
            if ((!user.getEmail().equals(dto.getEmail())) && (dao.checkFieldValueExists(EMAIL, user.getEmail()))) {
                if (!"".equals(sb.toString())) sb.append("; ");
                String error = ResourceBundle.getBundle(RB, locale).getString("error.busy-email");
                sb.append(error);
                if (getErrorMap() == null) setErrorMap(new HashMap<>());
                getErrorMap().put(EMAIL + "_" + MSG, error);
                getErrorMap().put(EMAIL, user.getEmail());
            }
            return sb.toString();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    public User findByCredentials(UserDto userDto) {
        return dao.findByCredentials(userDto.getUsername(), userDto.getPassword());
    }
}
