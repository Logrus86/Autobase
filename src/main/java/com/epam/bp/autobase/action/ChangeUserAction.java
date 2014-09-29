package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeUserAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ChangeUserAction.class);
    private static final ActionResult CABINET_USER = new ActionResult("cabinet",true);
    private static final ActionResult CABINET_DRIVER = new ActionResult("main-driver",true);
    private static final ActionResult MAIN_ADMIN = new ActionResult("main", true);
    private static final String RB_NAME = "i18n.text";
    private static final String LOCALE = "locale";
    private static String error_busy_username;
    private static final String ERROR = "user_change_error";
    private static final String USER = "user";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String DOB = "dob";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String BALANCE = "balance";
    private static final String ROLE = "role";
    private static final String SAVE = "save";
    private static final String DELETE = "delete";
    private static final String ENTITY_CHANGES_FLAG = "listsChanged";
    private ActionResult result;
    private User user;
    private HttpServletRequest request;
    private HttpSession session;

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        request = req;
        session = req.getSession();
        Locale locale = (Locale) session.getAttribute(LOCALE);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        error_busy_username = RB.getString("error.busy-username");

        user = (User) session.getAttribute(USER);
        //changing user if we are client or driver; if we are admin, do it if SAVE parameter not null only
        if (!user.getRole().equals(User.Role.ADMIN) || request.getParameter(SAVE) != null) {
            //validate data
            String error = Validator.validateRequestParametersMap(request);
            if (!error.isEmpty()) {
                session.setAttribute(ERROR, error);
                if (user.getRole() == User.Role.CLIENT) return CABINET_USER;
                if (user.getRole() == User.Role.DRIVER) return CABINET_DRIVER;
                return MAIN_ADMIN;
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
                String firstname = request.getParameter(FIRSTNAME);
                String lastname = request.getParameter(LASTNAME);
                String dob = request.getParameter(DOB);
                String username = request.getParameter(USERNAME);
                String password = request.getParameter(PASSWORD);
                String email = request.getParameter(EMAIL);
                Integer id = null;
                BigDecimal balance = null;
                User.Role role = null;
                if (user.getRole().equals(User.Role.CLIENT))
                    balance = new BigDecimal(request.getParameter(BALANCE));
                //if save not null it means we are admin changing user, set this case fields:
                if (request.getParameter(SAVE) != null) {
                    id = Integer.parseInt(request.getParameter(SAVE));
                    balance = new BigDecimal(request.getParameter(BALANCE));
                    role = User.Role.valueOf(request.getParameter(ROLE));
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
                        session.setAttribute(USER, user);
                    }
                    userDao.update(user);
                    session.setAttribute(ERROR, "");
                    session.setAttribute(ENTITY_CHANGES_FLAG, USER);
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at changeUser() while performing transaction");
            throw new ActionException("Error at changeUser() while performing transaction", e);
        }
        if (User.Role.CLIENT.equals(user.getRole())) result = CABINET_USER;
        if (User.Role.DRIVER.equals(user.getRole())) result = CABINET_DRIVER;
        if (request.getParameter(SAVE) != null) result = MAIN_ADMIN;
    }

    private void deleteUser() throws ActionException {
        Integer id = Integer.valueOf(request.getParameter(DELETE));
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            VehicleDao vehicleDao = daoManager.getVehicleDao();
            UserDao userDao = daoManager.getUserDao();
            daoManager.transactionAndClose(daoManager1 -> {
                //find vehicle linked with our user by driver-id field and set its field DRIVERID to null
                Vehicle vehicle = vehicleDao.getByDriverId(id);
                if (vehicle != null) {
                    vehicle.setDriverId(null);
                    vehicleDao.update(vehicle);
                }
                //and we can delete our user now
                userDao.delete(id);
                session.setAttribute(ENTITY_CHANGES_FLAG, USER);
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at deleteUser() while performing transaction");
            throw new ActionException("Error at deleteUser() while performing transaction", e);
        }
        result = MAIN_ADMIN;
    }
}
