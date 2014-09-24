package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.H2DaoManager;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.dao.VehicleDao;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.entity.Vehicle;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ResourceBundle;

public class ChangeUserAction implements Action {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    private ActionResult cabinet_user = new ActionResult("cabinet");
    private ActionResult cabinet_driver = new ActionResult("main-driver");
    private ActionResult main_admin = new ActionResult("main", true);
    private ActionResult result;
    private static final String ERROR_BUSY_USERNAME = ResourceBundle.getBundle("i18n.text").getString("error.busy-username");
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
    private User user;
    private HttpServletRequest request;
    private HttpSession session;

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        request = req;
        session = req.getSession();
        user = (User) session.getAttribute(USER);
        //validate data
        String error = Validator.validateRequestParametersMap(request);
        if (!error.isEmpty()) {
            session.setAttribute(ERROR, error);
            if (user.getRole() == User.Role.CLIENT) return cabinet_user;
            if (user.getRole() == User.Role.DRIVER) return cabinet_driver;
            return main_admin;
        }
        //changing user if we are client or driver; if we are admin, do it if SAVE parameter not null only
        if (!user.getRole().equals(User.Role.ADMIN) || request.getParameter(SAVE) != null) {
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
            H2DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(new H2DaoManager.DaoCommand() {
                @Override
                public void execute(H2DaoManager daoManager) throws DaoException {
                    UserDao userDao = daoManager.getUserDao();
                    //if save not null it means we are admin changing user, set this case fields:
                    Integer id = null;
                    BigDecimal balance = null;
                    User.Role role = null;
                    if (request.getParameter(SAVE) != null) {
                        id = Integer.parseInt(request.getParameter(SAVE));
                        balance = new BigDecimal(request.getParameter(BALANCE));
                        role = User.Role.valueOf(request.getParameter(ROLE));
                        //we must replace user from session with user which must be changed if we are admin:
                        user = userDao.getById(id);
                    }
                    String firstname = request.getParameter(FIRSTNAME);
                    String lastname = request.getParameter(LASTNAME);
                    String dob = request.getParameter(DOB);
                    String username = request.getParameter(USERNAME);
                    String password = request.getParameter(PASSWORD);
                    String email = request.getParameter(EMAIL);
                    if (user.getRole().equals(User.Role.CLIENT))
                        balance = new BigDecimal(request.getParameter(BALANCE));
                    // check username not busy if it was renewed
                    if ((!user.getUsername().equals(username)) && (!userDao.getUsersListByUsername(username).isEmpty())) {
                        session.setAttribute(ERROR, ERROR_BUSY_USERNAME);
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
                        session.setAttribute(ENTITY_CHANGES_FLAG, "true");
                    }
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at changeUser() while performing transaction");
            throw new ActionException("Error at changeUser() while performing transaction", e);
        }
        if (User.Role.CLIENT.equals(user.getRole())) result = cabinet_user;
        if (User.Role.DRIVER.equals(user.getRole())) result = cabinet_driver;
        if (request.getParameter(SAVE) != null) result = main_admin;
        session.setAttribute(ENTITY_CHANGES_FLAG, "true");
    }

    private void deleteUser() throws ActionException{
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            H2DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(new H2DaoManager.DaoCommand() {
                @Override
                public void execute(H2DaoManager daoManager) throws DaoException {
                    Integer id = Integer.valueOf(request.getParameter(DELETE));
                    //find vehicle linked with our user by driver-id field and set its field DRIVERID to null
                    VehicleDao vehicleDao = daoManager.getVehicleDao();
                    Vehicle vehicle = vehicleDao.getByDriverId(id);
                    if (vehicle != null) {
                        vehicle.setDriverId(null);
                        vehicleDao.update(vehicle);
                    }
                    //and we can delete our user now
                    UserDao userDao = daoManager.getUserDao();
                    userDao.delete(id);
                    session.setAttribute(ENTITY_CHANGES_FLAG, "true");
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at deleteUser() while performing transaction");
            throw new ActionException("Error at deleteUser() while performing transaction", e);
        }
        result = main_admin;
    }
}
