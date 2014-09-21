package com.epam.bp.autobase.action;

import com.epam.bp.autobase.dao.DaoFactory;
import com.epam.bp.autobase.dao.H2.H2UserDao;
import com.epam.bp.autobase.dao.UserDao;
import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.util.Validator;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class RegisterAction implements Action {

    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    private ActionResult registerSuccess = new ActionResult("registered");
    private ActionResult registerFailed = new ActionResult("register");
    private static final ResourceBundle RB = ResourceBundle.getBundle("i18n.text");
    private static final String ERROR_BUSY_USERNAME = RB.getString("error.busy-username");

    public RegisterAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();

        String error = Validator.validateRequestParametersMap(request);
        if (!error.isEmpty()) {
            session.setAttribute("reg_error", error);
            forwardEnteredData(request,session);
            return registerFailed;
        }

        //check username not busy
        try {
            UserDao userDao = DaoFactory.getInstance().getDaoManager().getUserDao();
            Map<String, String> params = new TreeMap<>();
            params.put("username", request.getParameter("username"));
            List<User> users = userDao.findByParams(params);
            if (!users.isEmpty()) {
                session.setAttribute("reg_error", ERROR_BUSY_USERNAME);
                forwardEnteredData(request,session);
                return registerFailed;
            }
        } catch (Exception e) {
            LOGGER.error("Error at RegisterAction while searching for username at database");
            throw new ActionException("Error at RegisterAction while searching for username at database", e);
        }

        //data was entered correctly, proceed
        User newUser = new User();
        newUser.setId(4);
        newUser.setFirstname(request.getParameter("firstname"));
        newUser.setLastname(request.getParameter("lastname"));
        newUser.setDob(request.getParameter("dob"));
        newUser.setUsername(request.getParameter("username"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setRole(User.Role.CLIENT);
        newUser.setBalance(BigDecimal.ZERO);

        H2UserDao h2UserDao;
        try {
            UserDao userDao = DaoFactory.getInstance().getDaoManager().getUserDao();
            userDao.create(newUser);
        } catch (Exception e) {
            LOGGER.info("Error at RegisterAction while creating user");
            throw new ActionException("Error at RegisterAction while creating user", e);
        }

        session.setAttribute("user", newUser);
        session.setAttribute("errormsg","");
        clearEnteredData(session);
        LOGGER.info(newUser.toString());
        return registerSuccess;
    }

    //forward entered registration data if some of it was entered wrong
    private void forwardEnteredData(HttpServletRequest request, HttpSession session) {
        session.setAttribute("firstname", request.getParameter("firstname"));
        session.setAttribute("lastname",request.getParameter("lastname"));
        session.setAttribute("dob",request.getParameter("dob"));
        session.setAttribute("username",request.getParameter("username"));
        session.setAttribute("email",request.getParameter("email"));
    }
    private void clearEnteredData(HttpSession session) {
        session.setAttribute("firstname","");
        session.setAttribute("lastname","");
        session.setAttribute("dob","");
        session.setAttribute("username","");
        session.setAttribute("email","");
    }
}