package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoException;
import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.H2DaoManager;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.entity.User;
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
    private static final String ERROR_BUSY_USERNAME = ResourceBundle.getBundle("i18n.text").getString("error.busy-username");
    private static final String ERROR = "cab_error";
    private static final String USER = "user";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String DOB = "dob";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String BALANCE = "balance";
    private static final String ENTITY_CHANGES_FLAG = "listsChanged";

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        //validate data
        String error = Validator.validateRequestParametersMap(request);
        if (!error.isEmpty()) {
            session.setAttribute(ERROR, error);
            if (user.getRole() == User.Role.CLIENT) return cabinet_user;
            if (user.getRole() == User.Role.DRIVER) return cabinet_driver;
        }

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            H2DaoManager daoManager = daoFactory.getDaoManager();
            daoManager.transactionAndClose(new H2DaoManager.DaoCommand() {
                @Override
                public void execute(H2DaoManager daoManager) throws DaoException {
                    String firstname = request.getParameter(FIRSTNAME);
                    String lastname = request.getParameter(LASTNAME);
                    String dob = request.getParameter(DOB);
                    String username = request.getParameter(USERNAME);
                    String password = request.getParameter(PASSWORD);
                    String email = request.getParameter(EMAIL);
                    BigDecimal balance = null;
                    if (user.getRole().equals(User.Role.CLIENT)) balance = new BigDecimal(request.getParameter(BALANCE));
                    UserDao userDao = daoManager.getUserDao();
                    // check username not busy if it was renewed
                    if ((!user.getUsername().equals(username)) && (!userDao.getUsersListByUsername(username).isEmpty())) {
                        session.setAttribute(ERROR, ERROR_BUSY_USERNAME);
                        session.setAttribute(USERNAME, "");
                    } else {
                        //data was entered correctly, proceed
                        user.setFirstname(firstname);
                        user.setLastname(lastname);
                        user.setDob(dob);
                        user.setUsername(username);
                        user.setPassword(password);
                        user.setEmail(email);
                        if (user.getRole().equals(User.Role.CLIENT)) user.setBalance(balance);
                        userDao.update(user);
                        session.setAttribute(USER, user);
                        session.setAttribute(ERROR, "");
                        session.setAttribute(ENTITY_CHANGES_FLAG, "true");
                    }
                }
            });
            daoFactory.releaseContext();
        } catch (Exception e) {
            LOGGER.error("Error at ChangeUserAction while performing transaction");
            throw new ActionException("Error at ChangeUserAction while performing transaction", e);
        }
        if (User.Role.CLIENT.equals(user.getRole())) return cabinet_user;
        return cabinet_driver;
    }
}
