package com.epam.bp.autobase.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("GET/login", new ShowPageAction("main"));
        actions.put("GET/register", new ShowPageAction("register"));
        actions.put("GET/registered", new ShowPageAction("registered"));
   //     actions.put("GET/search", new ShowPageAction("main-client"));
        actions.put("GET/admin-vehicles", new ShowPageAction("admin-vehicles"));
        actions.put("GET/admin-colors", new ShowPageAction("admin-colors"));
        actions.put("GET/admin-models", new ShowPageAction("admin-models"));
        actions.put("GET/admin-manufacturers", new ShowPageAction("admin-manufacturers"));

        actions.put("GET/main", new CheckLoginAction());
        actions.put("GET/quit", new LogoutAction());
        actions.put("GET/search", new SearchAction());
        actions.put("GET/cabinet", new ClearCabinetAction());

        actions.put("POST/login", new LoginAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("POST/change_user", new ChangeUserAction());
        actions.put("POST/change_vehicle", new ChangeVehicleAction());
    }

    public static Action getAction(String actionName) {
        return actions.get(actionName);
    }
}
