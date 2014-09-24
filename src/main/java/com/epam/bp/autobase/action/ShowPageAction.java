package com.epam.bp.autobase.action;

import javax.servlet.http.HttpServletRequest;

public class ShowPageAction implements Action {
    private ActionResult result;

    public ShowPageAction(String page) {
        result = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req) {
        return result;
    }
}