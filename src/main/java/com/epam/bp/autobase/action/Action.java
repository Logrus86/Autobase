package com.epam.bp.autobase.action;

import javax.servlet.http.HttpServletRequest;

public interface Action {
    ActionResult execute(HttpServletRequest request);
}
