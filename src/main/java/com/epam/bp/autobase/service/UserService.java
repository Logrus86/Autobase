package com.epam.bp.autobase.service;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.dao.hibernate.HibernateUserDao;
import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.model.entity.User;
import org.jboss.logging.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

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
        delete(id, dao, event);
    }

    @Override
    public void delete(UserDto dto) throws ServiceException {
        delete(dto, dao, event);
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
                .setBalance(dto.getBalance());
        if (dto.getId() != null) {
            user.setId(dto.getId());
        }
        // if (dto.getVehicleDtoList() != null) user.setVehicles(); TODO: reverse operation
        // if (dto.getOrderDtoList() != null) user.setOrders();
        return user;
    }

    @Override
    public String checkAllFieldsNotBusy(User user) throws ServiceException {
        return null;
    }

    @Override
    public String checkChangedFieldsNotBusy(User user, UserDto dto) throws ServiceException {
        return null;
    }

    public User findByCredentials(UserDto userDto) {
        return dao.findByCredentials(userDto.getUsername(), userDto.getPassword());
    }
    
    /* public void create(Map<String, String> userDataMap) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        initNewUser();
        user.setFirstname(userDataMap.get(FIRSTNAME))
                .setLastname(userDataMap.get(LASTNAME))
                .setDob(userDataMap.get(DOB))
                .setUsername(userDataMap.get(USERNAME))
                .setPassword(userDataMap.get(PASSWORD))
                .setEmail(userDataMap.get(EMAIL))
                .setRole(User.Role.CLIENT)
                .setBalance(BigDecimal.ZERO);
        //ejb validation
        Set<ConstraintViolation<User>> cvs = validator().validateWhileCreate(user);
        errorMap = userDataMap;
        for (ConstraintViolation<User> cv : cvs) {
            sb.append(cv.getMessage()).append(": ").append(cv.getInvalidValue()).append("; ");
            errorMap.put(cv.getPropertyPath() + "_" + MSG, cv.getMessage());
        }
        //check password and password-repeat are the same
        if (!userDataMap.get(PASSWORD).equals(userDataMap.get(PASSWORD_REPEAT))) {
            String error = ResourceBundle.getBundle(RB).getString("error.passes-not-equals");
            sb.append(error);
            errorMap.put(PASSWORD + "_" + MSG, error);
        }
        // check busyness of username
        if (checkFieldValueExists(USERNAME, user.getUsername())) {
            String error = ResourceBundle.getBundle(RB).getString("error.busy-username");
            sb.append(error);
            errorMap.put(USERNAME + "_" + MSG, error);
        }
        // check busyness of email
        if (checkFieldValueExists(EMAIL, user.getEmail())) {
            String error = ResourceBundle.getBundle(RB).getString("error.busy-email");
            sb.append(error);
            errorMap.put(EMAIL + "_" + MSG, error);
        }
        // if sb is empty all is ok, proceed to persist
        if (sb.toString().isEmpty()) {
            em.persist(user);
            ss.setSessionUser(user);
            event.fire(user);
            errorMap = null;
            //...or clear user and throw exception
        } else {
            initNewUser();
            throw new ServiceException("Error while user registration: " + sb.toString());
        }
    }

    @PostConstruct
    public void initNewUser() {
        user = new User();
    }

    public boolean checkFieldValueExists(String field, String value) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq(field, value));
        return !criteria.list().isEmpty();
    }

    public void update(Map<String, String> userDataMap) throws ServiceException {
        StringBuilder sb = new StringBuilder();
        User user = new User()
                .setFirstname(userDataMap.get(FIRSTNAME))
                .setLastname(userDataMap.get(LASTNAME))
                .setDob(userDataMap.get(DOB))
                .setEmail(userDataMap.get(EMAIL))
                .setUsername(userDataMap.get(USERNAME))
                .setPassword(userDataMap.get(PASSWORD));
        String balance = userDataMap.get(BALANCE);
        if (balance != null) user.setBalance(new BigDecimal(userDataMap.get(BALANCE)));
        String role = userDataMap.get(ROLE);
        if (role != null) user.setRole(role);
        String idString = userDataMap.get(ID);
        if (userDataMap.get(ID) != null) user.setId(Integer.parseInt(idString));
        //ejb validation
        errorMap = new HashMap<>();
        Set<ConstraintViolation<User>> cvs = validator().validateWhileCreate(user);
        for (ConstraintViolation<User> cv : cvs) {
            sb.append(cv.getMessage()).append(": ").append(cv.getInvalidValue()).append("; ");
            errorMap.put(cv.getPropertyPath() + "_" + MSG, cv.getMessage());
            try {
                String propertyValue = String.valueOf(User.class.getMethod("get" + cv.getPropertyPath()).invoke(null));
                errorMap.put(cv.getPropertyPath().toString(), propertyValue);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }
        // if username was changed
        if (!em.find(User.class, user.getId()).getUsername().equals(user.getUsername())) {
            // check busyness of new username
            if (checkFieldValueExists(USERNAME, user.getUsername())) {
                String error = ResourceBundle.getBundle(RB).getString("error.busy-username");
                sb.append(error);
                errorMap.put(USERNAME + "_" + MSG, error);
            }
        }
        // if email was changed
        if (!em.find(User.class, user.getId()).getEmail().equals(user.getEmail())) {
            //check busyness of new email
            if (checkFieldValueExists(EMAIL, user.getEmail())) {
                String error = ResourceBundle.getBundle(RB).getString("error.busy-email");
                sb.append(error);
                errorMap.put(EMAIL + "_" + MSG, error);
            }
        }
        // if sb is empty all is ok, proceed to persist
        if (sb.toString().isEmpty()) {
            em.merge(user);
            event.fire(user);
            errorMap = null;
            //...or throw ServiceException
        } else {
            throw new ServiceException("Error while user updating: " + sb.toString());
        }
    }

    public void delete(Integer id) {
        // retrieve user by id
        User userToDelete = em.find(User.class, id);
        // unlink his vehicles if he is driver
        if ((userToDelete.getRole().equals(User.Role.DRIVER)) & (!userToDelete.getVehicles().isEmpty())) {
            for (Vehicle vehicle : userToDelete.getVehicles()) {
                vehicle.setDriver(null);
            }
        }
        em.remove(userToDelete);
        event.fire(userToDelete);
    }*/
}
