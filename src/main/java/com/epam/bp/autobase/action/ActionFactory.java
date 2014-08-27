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
    }

    public static Action getAction(String actionName) {
        return actions.get(actionName);
    }
}
