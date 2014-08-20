package com.epam.bp.autobase.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("GET/exampleText", new ShowPageAction("exampleText"));
        actions.put("POST/repeatText", new RepeatTextAction());

        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("POST/login", new LoginAction());
        actions.put("GET/register", new ShowPageAction("register"));
        actions.put("POST/register", new RegisterAction());
    }

    public static Action getAction(String actionName) {
        return actions.get(actionName);
    }
}
