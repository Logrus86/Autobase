package com.epam.bp.autobase.gwt.client.rpc;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.place.Client;
import com.epam.bp.autobase.model.dto.UserDto;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.gwtbootstrap3.client.ui.FormGroup;
import org.gwtbootstrap3.client.ui.base.AbstractTextWidget;
import org.gwtbootstrap3.client.ui.constants.ValidationState;

public class LoginCallback implements AsyncCallback<UserDto> {
    Presenter listener;
    AbstractTextWidget widget_loginResult;
    FormGroup loginInputs;

    public LoginCallback(Presenter listener, FormGroup loginInputs, AbstractTextWidget widget_loginResult) {
        this.listener = listener;
        this.widget_loginResult = widget_loginResult;
        this.loginInputs = loginInputs;
    }

    public void onSuccess(UserDto user) {
        if (user != null) listener.goTo(new Client("main"), user);
        else {
            loginInputs.setValidationState(ValidationState.ERROR);
            widget_loginResult.setText("User with such credentials wasn't found");
        }
    }

    public void onFailure(Throwable throwable) {
        widget_loginResult.setText("Failed to receive answer from server: " + throwable.getMessage());
    }

}
