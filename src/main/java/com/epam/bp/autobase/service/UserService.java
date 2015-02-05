package com.epam.bp.autobase.service;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.dao.hibernate.Hibernate;
import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.model.entity.Vehicle;
import org.jboss.logging.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Locale;

@Model
public class UserService extends AbstractService<User, UserDto, UserDao> {

    @Inject
    Logger logger;
    @Inject
    @Hibernate
    private UserDao dao;
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
        update(dto, dao, event, ss.getLocale());
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            User user = dao.getById(id);
            unLinkVehicles(user);
            delete(id, dao, event);
        } catch (DaoException e) {
            throw new ServiceException("User with id=" + id + " wasn't found. Abort.");
        }
    }

    @Override
    public void delete(UserDto dto) throws ServiceException {
        User user = dto.buildLazyEntity();
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
        sb.append(checkFieldNotBusy(USERNAME, user.getUsername(), dao, locale));
        if (sb.length() != 0) sb.append("; ");
        sb.append(checkFieldNotBusy(EMAIL, user.getEmail(), dao, locale));
        return sb.toString();
    }

    @Override
    public String checkChangedFieldsNotBusy(User user, UserDto dto) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        Locale locale = ss.getLocale();
        if (!user.getUsername().equals(dto.getUsername()))
            sb.append(checkFieldNotBusy(USERNAME, dto.getUsername(), dao, locale));
        if (sb.length() != 0) sb.append("; ");
        if (!user.getEmail().equals(dto.getEmail()))
            sb.append(checkFieldNotBusy(EMAIL, dto.getEmail(), dao, locale));
        return sb.toString();
    }

    public User findByCredentials(UserDto userDto) throws ServiceException {
        try {
            return dao.findByCredentials(userDto.getUsername(), userDto.getPassword());
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
