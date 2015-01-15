package com.epam.bp.autobase.action;

import com.epam.bp.autobase.util.AttributeSetter;

import javax.inject.Inject;
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
        //        as.setToSession(ORDER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_BUSES : {
        //        as.setToSession(BUS, session);
        //        as.setToSession(USER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_CARS: {
        //        as.setToSession(CAR, session);
        //        as.setToSession(USER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_TRUCKS : {
        //        as.setToSession(TRUCK, session);
        //        as.setToSession(USER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_USERS : {
         //       as.setToSession(USER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_ORDERS : {
         //       as.setToSession(ORDER, session);
         //       as.setToSession(USER, session);
                break;
            }
            case ActionFactory.PAGE_MAIN_DRIVER : {
         //       as.setToSession(DRIVER_VEHICLES, session);
                break;
            }
        }
        return result;
    }

}