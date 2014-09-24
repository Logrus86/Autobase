package com.epam.bp.autobase.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Map<String, Action> ACTIONS = new HashMap<>();

    static {
        ACTIONS.put("GET/login", new ShowPageAction("main"));
        ACTIONS.put("GET/register", new ShowPageAction("register"));
        ACTIONS.put("GET/registered", new ShowPageAction("registered"));
        ACTIONS.put("GET/admin-vehicles", new ShowPageAction("admin-vehicles"));
        ACTIONS.put("GET/admin-colors", new ShowPageAction("admin-colors"));
        ACTIONS.put("GET/admin-models", new ShowPageAction("admin-models"));
        ACTIONS.put("GET/admin-manufacturers", new ShowPageAction("admin-manufacturers"));
        ACTIONS.put("GET/admin-cars", new ShowPageAction("admin-cars"));
        ACTIONS.put("GET/admin-buses", new ShowPageAction("admin-buses"));
        ACTIONS.put("GET/admin-trucks", new ShowPageAction("admin-trucks"));


        ACTIONS.put("GET/main", new CheckLoginAction());
        ACTIONS.put("GET/search", new SearchAction());
        ACTIONS.put("GET/cabinet", new CabinetAction());
        ACTIONS.put("GET/quit", new LogoutAction());

        ACTIONS.put("POST/login", new LoginAction());
        ACTIONS.put("POST/register", new RegisterAction());
        ACTIONS.put("POST/change_user", new ChangeUserAction());
        ACTIONS.put("POST/change_vehicle", new ChangeVehicleAction());
        ACTIONS.put("POST/change_color", new ChangePropAction("color"));
        ACTIONS.put("POST/change_model", new ChangePropAction("model"));
        ACTIONS.put("POST/change_manufacturer", new ChangePropAction("manufacturer"));
    }

    public static Action getAction(String actionName) {
        return ACTIONS.get(actionName);
    }
}
