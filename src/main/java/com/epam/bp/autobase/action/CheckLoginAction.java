package com.epam.bp.autobase.action;

import com.epam.bp.autobase.entity.User;
import com.epam.bp.autobase.util.AttributeSetter;
import com.epam.bp.autobase.util.AttributeSetterException;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckLoginAction implements Action {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CheckLoginAction.class);
    private static final ActionResult LOGIN_ADMIN = new ActionResult(ActionFactory.PAGE_ADMIN_ORDERS);
    private static final ActionResult LOGIN_CLIENT = new ActionResult(ActionFactory.PAGE_MAIN_CLIENT);
    private static final ActionResult LOGIN_DRIVER = new ActionResult(ActionFactory.PAGE_MAIN_DRIVER);
    private static final ActionResult LOGIN_FALSE = new ActionResult(ActionFactory.PAGE_MAIN);
    private static final String LOGIN_ERROR = "errormsg";
    private static final String SEARCH_ERROR = "search_error";
    private static final String CREATE_ERROR = "create_error";
    private static final String USER = "user";
    private static final String ORDER = "order";
    private static final String DRIVER_VEHICLES = "driverVehicles";
    @Inject
    AttributeSetter as;

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        HttpSession session = request.getSession();
        session.removeAttribute(LOGIN_ERROR);
        session.removeAttribute(SEARCH_ERROR);
        session.removeAttribute(CREATE_ERROR);

        if (session.getAttribute(USER) == null) {
            LOGGER.info("User not logined, going to main page");
            return LOGIN_FALSE;
        }
        User user = (User) session.getAttribute(USER);
        if (user.getRole() == User.Role.ADMIN) {
            try {
                as.setToSession(ORDER, session);
                as.setToSession(USER, session);
            } catch (AttributeSetterException e) {
                throw new ActionException(e);
            }
            return LOGIN_ADMIN;
        }
        if (user.getRole() == User.Role.CLIENT) return LOGIN_CLIENT;
        if (user.getRole() == User.Role.DRIVER) {
            try {
                as.setToSession(DRIVER_VEHICLES, session);
            } catch (AttributeSetterException e) {
                throw new ActionException(e);
            }
            return LOGIN_DRIVER;
        }
        return LOGIN_FALSE;
    }
}
