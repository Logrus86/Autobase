package com.epam.bp.autobase.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("GET/main", new CheckLoginAction());
        actions.put("GET/login", new ShowPageAction("main"));
        actions.put("POST/login", new LoginAction());
        actions.put("GET/register", new ShowPageAction("register"));
        actions.put("POST/register", new RegisterAction());
        actions.put("GET/registered", new ShowPageAction("registered"));
        actions.put("GET/quit", new LogoutAction());
        actions.put("GET/search", new ShowPageAction("main-client"));
        actions.put("POST/search", new SearchAction());
        actions.put("GET/cabinet", new ClearCabinetAction());
        actions.put("POST/update_user", new UpdateUserAction());
        actions.put("POST/update_vehicle", new UpdateVehicleAction());
        actions.put("GET/change_user", new ChangeUserAction());
        actions.put("GET/admin-vehicles", new ShowPageAction("admin-vehicles"));
        actions.put("GET/admin-colors", new ShowPageAction("admin-colors"));
    }

    public static Action getAction(String actionName) {
        return actions.get(actionName);
    }
}
