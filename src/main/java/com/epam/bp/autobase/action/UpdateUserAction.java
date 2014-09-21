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

public class UpdateUserAction implements Action {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
    private ActionResult cabinet_user = new ActionResult("cabinet");
    private ActionResult cabinet_driver = new ActionResult("main-driver");
    private static final ResourceBundle RB = ResourceBundle.getBundle("i18n.text");
    private static final String ERROR_BUSY_USERNAME = RB.getString("error.busy-username");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String error = Validator.validateRequestParametersMap(request);
        if (!error.isEmpty()) {
            session.setAttribute("cab_error", error);
            if (user.getRole()==User.Role.CLIENT) return cabinet_user;
            if (user.getRole()==User.Role.DRIVER) return cabinet_driver;
        }
        //check username not busy
        try {
            UserDao userDao = DaoFactory.getInstance().getDaoManager().getUserDao();
            Map<String, String> params = new TreeMap<>();
            params.put("username", request.getParameter("username"));
            List<User> users = userDao.findByParams(params);
            if (users.size() > 1) {
                session.setAttribute("cab_error", ERROR_BUSY_USERNAME);
                session.setAttribute("username","");
                if (user.getRole()==User.Role.CLIENT) return cabinet_user;
                if (user.getRole()==User.Role.DRIVER) return cabinet_driver;
            }
        } catch (Exception e) {
            LOGGER.error("Error at UpdateUserAction while searching for username at database");
            throw new ActionException("Error at UpdateUserAction while searching for username at database", e);
        }
        //data was entered correctly, proceed
        user.setFirstname(request.getParameter("firstname"));
        user.setLastname(request.getParameter("lastname"));
        user.setDob(request.getParameter("dob"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        //TODO add money to balance with unique number card
        if (user.getRole()==User.Role.CLIENT) user.setBalance(BigDecimal.valueOf(Double.valueOf(request.getParameter("balance"))));
        H2UserDao h2UserDao;
        try {
            UserDao userDao = DaoFactory.getInstance().getDaoManager().getUserDao();
            userDao.update(user);
            session.setAttribute("user", user);
            session.setAttribute("cab_error", "");
        } catch (Exception e) {
            throw new ActionException("Error at UpdateUserAction while updating user '" + user.getUsername() + "'", e);
        }
        if (user.getRole()==User.Role.CLIENT) return cabinet_user;
        if (user.getRole()==User.Role.DRIVER) return cabinet_driver;
        return null;
    }
}
