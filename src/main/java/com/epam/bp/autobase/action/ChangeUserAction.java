package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.Entity;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.util.AttributeSetter;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeUserAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ChangeUserAction.class);
    private static final ActionResult CABINET_USER = new ActionResult(ActionFactory.PAGE_CABINET);
    private static final ActionResult CABINET_DRIVER = new ActionResult(ActionFactory.PAGE_MAIN_DRIVER);
    private static final ActionResult ADMIN_USERS = new ActionResult(ActionFactory.PAGE_ADMIN_USERS, true);
    private static final String RB_NAME = "i18n.text";
    private static final String LOCALE = "locale";
    private static final String ERROR = "user_change_error";
    private static final String SAVE = "save";
    private static final String DELETE = "delete";
    private static final String ERROR_BUSY_USERNAME = "error.busy-username";
    private static String error_busy_username;
    private ActionResult result;
    private User user;
    private HttpServletRequest request;
    private HttpSession session;

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        request = req;
        session = req.getSession();
        ServletContext context = session.getServletContext();
        Locale locale = (Locale) context.getAttribute(LOCALE);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        error_busy_username = RB.getString(ERROR_BUSY_USERNAME);

        user = (User) session.getAttribute(Entity.USER);
        //changing user if we are client or driver; if we are admin, do it if SAVE parameter not null only
        if (!user.getRole().equals(User.Role.ADMIN) || request.getParameter(SAVE) != null) {
            //validate data
            String error = Validator.validateRequestParametersMap(request);
            if (!error.isEmpty()) {
                session.setAttribute(ERROR, error);
                if (user.getRole() == User.Role.CLIENT) return CABINET_USER;
                if (user.getRole() == User.Role.DRIVER) return CABINET_DRIVER;
                return ADMIN_USERS;
            }
            changeUser();
        }
        //admin: if parameter delete not null, we are deleting user
        if ((user.getRole().equals(User.Role.ADMIN)) && (request.getParameter(DELETE) != null)) {
            deleteUser();
        }
        return result;
    }

    private void changeUser() throws ActionException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            UserDao userDao = daoManager.getUserDao();
            daoManager.transactionAndClose(daoManager1 -> {
                String firstname = request.getParameter(Entity.FIRSTNAME);
                String lastname = request.getParameter(Entity.LASTNAME);
                String dob = request.getParameter(Entity.DOB);
                String username = request.getParameter(Entity.USERNAME);
                String password = request.getParameter(Entity.PASSWORD);
                String email = request.getParameter(Entity.EMAIL);
                Integer id = null;
                BigDecimal balance = null;
                User.Role role = null;
                if (user.getRole().equals(User.Role.CLIENT))
                    balance = new BigDecimal(request.getParameter(Entity.BALANCE));
                //if save not null it means we are admin changing user, set this case fields:
                if (request.getParameter(SAVE) != null) {
                    id = Integer.parseInt(request.getParameter(SAVE));
                    balance = new BigDecimal(request.getParameter(Entity.BALANCE));
                    role = User.Role.valueOf(request.getParameter(Entity.ROLE));
                    //we must replace user from session with user which must be changed if we are admin:
                    user = userDao.getById(id);
                }
                // check username not busy if it was renewed
                if ((!user.getUsername().equals(username)) && (!userDao.getUsersListByUsername(username).isEmpty())) {
                    session.setAttribute(ERROR, error_busy_username);
                } else {
                    //data was entered correctly, proceed
                    user.setFirstname(firstname);
                    user.setLastname(lastname);
                    user.setDob(dob);
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(email);
                    if (user.getRole().equals(User.Role.CLIENT)) user.setBalance(balance);
                    if (request.getParameter(SAVE) != null) {
                        user.setBalance(balance);
                        user.setRole(role);
                        user.setId(id);
                    } else {
                        session.setAttribute(Entity.USER, user);
                        AttributeSetter.setEntityToSession(Entity.USER, session);
                    }
                    userDao.update(user);
                    session.setAttribute(ERROR, "");
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at changeUser() while performing transaction");
            throw new ActionException("Error at changeUser() while performing transaction", e);
        }
        if (User.Role.CLIENT.equals(user.getRole())) result = CABINET_USER;
        if (User.Role.DRIVER.equals(user.getRole())) result = CABINET_DRIVER;
        if (request.getParameter(SAVE) != null) result = ADMIN_USERS;
    }

    private void deleteUser() throws ActionException {
        Integer id = Integer.valueOf(request.getParameter(DELETE));
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            VehicleDao vehicleDao = daoManager.getVehicleDao();
            UserDao userDao = daoManager.getUserDao();
            daoManager.transactionAndClose(daoManager1 -> {
                //find vehicles linked with our user by driver-id field and set their field DRIVER_ID to null
                List<Vehicle> list = vehicleDao.getListByParameter(com.epam.bp.autobase.dao.H2.VehicleDao.DRIVER_ID, String.valueOf(id));
                if (!list.isEmpty()) {
                    for (Vehicle vehicle : list) {
                        vehicle.setDriverId(null);
                        vehicleDao.update(vehicle);
                    }
                }
                //and we can delete our user now
                userDao.delete(id);
            });
            daoFactory.releaseContext();
            AttributeSetter.setEntityToSession(Entity.USER, session);
        } catch (Exception e) {
            LOGGER.error("Error at deleteUser() while performing transaction");
            throw new ActionException("Error at deleteUser() while performing transaction", e);
        }
        result = ADMIN_USERS;
    }
}
