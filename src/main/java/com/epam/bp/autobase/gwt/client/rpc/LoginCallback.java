package com.epam.bp.autobase.gwt.client.rpc;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.place.Client;
import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.constants.ControlGroupType;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class LoginCallback implements AsyncCallback<UserDtoGwt> {
    Presenter listener;
    Widget widget_loginResult;
    ControlGroup loginInputs;

    public LoginCallback(Presenter listener, ControlGroup loginInputs, Widget widget_loginResult) {
        this.listener = listener;
        this.widget_loginResult = widget_loginResult;
        this.loginInputs = loginInputs;
    }

    public void onSuccess(UserDtoGwt user) {
        if (user != null) listener.goTo(new Client("main"), user);
        else {
            loginInputs.setType(ControlGroupType.ERROR);
            widget_loginResult.getElement().setInnerHTML("User with such credentials wasn't found.");
        }
    }

    public void onFailure(Throwable throwable) {
        widget_loginResult.getElement().setInnerHTML("Failed to receive answer from server: " + throwable.getMessage());
    }

}
