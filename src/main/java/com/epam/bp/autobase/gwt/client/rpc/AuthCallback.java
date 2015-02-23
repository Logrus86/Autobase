package com.epam.bp.autobase.gwt.client.rpc;

import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.client.ui.Fieldset;
import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class AuthCallback implements AsyncCallback<UserDtoGwt> {
    Label label_error;
    ControlLabel label_welcome;
    Fieldset fieldSetVisibleAfterLogin;
    Fieldset fieldSetInvisibleAfterLogin;
    UserDtoGwt user;

    public AuthCallback(Label label_error, Fieldset fieldSetVisibleAfterLogin, Fieldset fieldSetInvisibleAfterLogin) {
        this.label_error = label_error;
        this.fieldSetVisibleAfterLogin = fieldSetVisibleAfterLogin;
        this.fieldSetInvisibleAfterLogin = fieldSetInvisibleAfterLogin;
        for (Widget widget : fieldSetVisibleAfterLogin) {
            if (widget.getTitle().equals("label_welcome")) this.label_welcome = (ControlLabel) widget;
        }
    }

    public void onSuccess(UserDtoGwt result) {
        user = result;
        toggleWidgets();
    }

    public void onFailure(Throwable throwable) {
        label_error.setText("Failed to receive answer from server!");
    }

    private void toggleWidgets() {
        if (user != null) {
            label_welcome.getElement().setInnerHTML("Welcome, " + user.getUsername() + "!");
            if (label_error != null) label_error.setText(null);
            for (Widget widget : fieldSetVisibleAfterLogin) widget.setVisible(true);
            for (Widget widget : fieldSetInvisibleAfterLogin) widget.setVisible(false);
        } else {
            label_welcome.getElement().setInnerHTML(null);
            if (label_error != null) label_error.setText("User with such credentials wasn't found.");
            for (Widget widget : fieldSetVisibleAfterLogin) widget.setVisible(false);
            for (Widget widget : fieldSetInvisibleAfterLogin) widget.setVisible(true);
        }
    }
}
