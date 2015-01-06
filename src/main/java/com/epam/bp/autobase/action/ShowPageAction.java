package com.epam.bp.autobase.action;

import com.epam.bp.autobase.entity.Entity;
import com.epam.bp.autobase.util.AttributeSetter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowPageAction implements Action {
    private static final String ATTR_ERROR_USER_CHANGE = "user_change_error";

    private static final String ORDER = "order";
    private static final String DRIVER_VEHICLES = "driverVehicles";
    private static final String USER = "user";
    private static final String BUS = "Bus";
    private static final String CAR = "Car";
    private static final String TRUCK = "Truck";
    private ActionResult result;
    private String page;

    public ShowPageAction(String page) {
        result = new ActionResult(page);
        this.page = page;
    }

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        HttpSession session = req.getSession();
        switch (page) {
            case ActionFactory.PAGE_CABINET : {
                session.removeAttribute(ATTR_ERROR_USER_CHANGE);
                AttributeSetter.setEntityToSession(ORDER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_BUSES : {
                AttributeSetter.setEntityToSession(BUS, session);
                AttributeSetter.setEntityToSession(USER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_CARS: {
                AttributeSetter.setEntityToSession(CAR, session);
                AttributeSetter.setEntityToSession(USER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_TRUCKS : {
                AttributeSetter.setEntityToSession(TRUCK, session);
                AttributeSetter.setEntityToSession(USER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_USERS : {
                AttributeSetter.setEntityToSession(USER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_ORDERS : {
                AttributeSetter.setEntityToSession(ORDER, session);
                AttributeSetter.setEntityToSession(USER, session);
                break;
            }
            case ActionFactory.PAGE_MAIN_DRIVER : {
                AttributeSetter.setEntityToSession(DRIVER_VEHICLES, session);
                break;
            }
        }
        return result;
    }

}