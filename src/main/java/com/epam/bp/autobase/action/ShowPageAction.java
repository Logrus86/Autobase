package com.epam.bp.autobase.action;

import com.epam.bp.autobase.entity.Entity;
import com.epam.bp.autobase.util.AttributeSetter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowPageAction implements Action {
    private static final String ATTR_ERROR_USER_CHANGE = "user_change_error";
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
                AttributeSetter.setEntityToSession(Entity.ORDER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_BUSES : {
                AttributeSetter.setEntityToSession(Entity.BUS, session);
                AttributeSetter.setEntityToSession(Entity.USER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_CARS: {
                AttributeSetter.setEntityToSession(Entity.CAR, session);
                AttributeSetter.setEntityToSession(Entity.USER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_TRUCKS : {
                AttributeSetter.setEntityToSession(Entity.TRUCK, session);
                AttributeSetter.setEntityToSession(Entity.USER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_USERS : {
                AttributeSetter.setEntityToSession(Entity.USER, session);
                break;
            }
            case ActionFactory.PAGE_ADMIN_ORDERS : {
                AttributeSetter.setEntityToSession(Entity.ORDER, session);
                AttributeSetter.setEntityToSession(Entity.USER, session);
                break;
            }
            case ActionFactory.PAGE_MAIN_DRIVER : {
                AttributeSetter.setEntityToSession(Entity.DRIVER_VEHICLES, session);
                break;
            }
        }
        return result;
    }

}