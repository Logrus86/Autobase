package com.epam.bp.autobase.action;

import javax.servlet.http.HttpServletRequest;

public class ShowPageAction implements Action {
    private ActionResult actionResult;

    public ShowPageAction(String page) {
        actionResult = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req) {
        return actionResult;
    }
}