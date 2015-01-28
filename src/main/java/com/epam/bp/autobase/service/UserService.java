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
        User user = getEntityFromDto(dto);
        unLinkVehicles(user);
        delete(dto, dao, event);
    }

    private void unLinkVehicles(User user) {
        if ((user.getRole().equals(User.Role.DRIVER)) & (!user.getVehicles().isEmpty()))
            for (Vehicle vehicle : user.getVehicles()) vehicle.setDriver(null);
    }

    @Override
    public UserDto getDtoFromEntity(User user) {
        UserDto userDto = new UserDto()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setDobString(user.getDob())
                .setEmail(user.getEmail())
                .setRole(user.getRole())
                .setBalance(user.getBalance());
        if (user.getId() != null) userDto.setId(user.getId());
        // if (user.getVehicles() != null) userDto.setVehicleDtoList(user.getVehicles()); TODO: how it must be done ?
        // if (user.getOrders() != null) userDto.setOrderDtoList(
        return userDto;
    }

    @Override
    public User getEntityFromDto(UserDto dto) {
        User user = new User()
                .setUsername(dto.getUsername())
                .setPassword(dto.getPassword())
                .setFirstname(dto.getFirstname())
                .setLastname(dto.getLastname())
                .setDob(dto.getDobString())
                .setEmail(dto.getEmail())
                .setRole(dto.getRole())
                .setBalance(null);
        if (dto.getId() != null) {
            user.setId(dto.getId());
        }
        // if (dto.getVehicleDtoList() != null) user.setVehicles(); TODO: reverse operation
        // if (dto.getOrderDtoList() != null) user.setOrders();
        return user;
    }

    @Override
    public String checkAllFieldsNotBusy(User user) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        Locale locale = ss.getLocale();
        try {
            if (dao.checkFieldValueExists(USERNAME, user.getUsername())) {
                String error = ResourceBundle.getBundle(RB, locale).getString("error.busy-username");
                sb.append(error);
                getErrorMap().put(USERNAME + "_" + MSG, error);
            }
            if (dao.checkFieldValueExists(EMAIL, user.getEmail())) {
                if (!"".equals(sb.toString())) sb.append("; ");
                String error = ResourceBundle.getBundle(RB, locale).getString("error.busy-email");
                sb.append(error);
                getErrorMap().put(EMAIL + "_" + MSG, error);
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
            }
            if ((!user.getPassword().equals(dto.getPassword())) && (dao.checkFieldValueExists(PASSWORD, user.getPassword()))) {
                if (!"".equals(sb.toString())) sb.append("; ");
                String error = ResourceBundle.getBundle(RB, locale).getString("error.busy-password");
                sb.append(error);
                if (getErrorMap() == null) setErrorMap(new HashMap<>());
                getErrorMap().put(PASSWORD + "_" + MSG, error);
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
