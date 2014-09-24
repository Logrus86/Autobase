package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.DaoManager;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ResourceBundle;

public class RegisterAction implements Action {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RegisterAction.class);
    private static final ActionResult REG_SUCCESS = new ActionResult("registered");
    private static final ActionResult REG_FAILED = new ActionResult("register");
    private static final String ERROR_BUSY_USERNAME = ResourceBundle.getBundle("i18n.text").getString("error.busy-username");
    private static final String ERROR = "reg_error";
    private static final String USER = "user";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String DOB = "dob";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ENTITY_CHANGES_FLAG = "listsChanged";
    private ActionResult result;

    public RegisterAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        //validate inputs
        String error = Validator.validateRequestParametersMap(request);
        if (!error.isEmpty()) {
            session.setAttribute(ERROR, error);
            forwardEnteredData(request, session);
            return REG_FAILED;
        }

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public void execute(DaoManager daoManager) throws DaoException {
                    UserDao userDao = daoManager.getUserDao();
                    String firstname = request.getParameter(FIRSTNAME);
                    String lastname = request.getParameter(LASTNAME);
                    String dob = request.getParameter(DOB);
                    String username = request.getParameter(USERNAME);
                    String password = request.getParameter(PASSWORD);
                    String email = request.getParameter(EMAIL);
                    // check username not busy
                    if (userDao.getUsersListByUsername(username).size() > 1) {
                        session.setAttribute(ERROR, ERROR_BUSY_USERNAME);
                        forwardEnteredData(request, session);
                        result = REG_FAILED;
                    } //data was entered correctly and username not busy, proceed
                    else {
                        User user = new User();
                        user.setFirstname(firstname);
                        user.setLastname(lastname);
                        user.setDob(dob);
                        user.setUsername(username);
                        user.setPassword(password);
                        user.setEmail(email);
                        user.setRole(User.Role.CLIENT);
                        user.setBalance(BigDecimal.ZERO);
                        userDao.create(user);
                        //get user from db to get his db ID to entity
                        user = userDao.getUsersListByUsername(username).get(0);
                        session.setAttribute(USER, user);
                        session.setAttribute(ERROR, "");
                        clearEnteredData(session);
                        LOGGER.info("Newly registered user: " + user.toString());
                        result = REG_SUCCESS;
                        session.setAttribute(ENTITY_CHANGES_FLAG, "true");
                    }
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at RegisterAction while performing transaction");
            throw new ActionException("Error at RegisterAction while performing transaction", e);
        }
        return result;
    }

    //forward entered registration data if some of it was entered wrong
    private void forwardEnteredData(HttpServletRequest request, HttpSession session) {
        session.setAttribute(FIRSTNAME, request.getParameter(FIRSTNAME));
        session.setAttribute(LASTNAME, request.getParameter(LASTNAME));
        session.setAttribute(DOB, request.getParameter(DOB));
        session.setAttribute(USERNAME, request.getParameter(USERNAME));
        session.setAttribute(EMAIL, request.getParameter(EMAIL));
    }

    private void clearEnteredData(HttpSession session) {
        session.removeAttribute(FIRSTNAME);
        session.removeAttribute(LASTNAME);
        session.removeAttribute(DOB);
        session.removeAttribute(USERNAME);
        session.removeAttribute(EMAIL);
    }
}