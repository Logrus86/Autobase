package com.epam.bp.autobase.gwt.client.rpc;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.place.Client;
import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class LoginCallback implements AsyncCallback<UserDtoGwt> {
    Presenter listener;
    Widget label_loginResult;

    public LoginCallback(Presenter listener, Widget label_loginResult) {
        this.listener = listener;
        this.label_loginResult = label_loginResult;
    }

    public void onSuccess(UserDtoGwt user) {
        if (user != null) listener.goTo(new Client("main"), user);
        else label_loginResult.getElement().setInnerHTML("User with such credentials wasn't found.");
    }

    public void onFailure(Throwable throwable) {
        label_loginResult.getElement().setInnerHTML("Failed to receive answer from server: " + throwable.getMessage());
    }

}
