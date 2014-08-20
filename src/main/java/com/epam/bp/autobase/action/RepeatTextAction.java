package com.epam.bp.autobase.action;

import javax.servlet.http.HttpServletRequest;

public class RepeatTextAction implements Action {

    private ActionResult actionResult = new ActionResult("repeatText");

    public RepeatTextAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest request) {
        String text = request.getParameter("text");
        request.setAttribute("text", text);
        return actionResult;
    }
}