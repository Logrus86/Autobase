package com.epam.bp.autobase.gwt.client.rpc;

import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.client.ui.Fieldset;
import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class AuthCallback implements AsyncCallback<String> {
    Label label_error;
    ControlLabel label_welcome;
    Fieldset fieldSetVisibleAfterLogin;
    Fieldset fieldSetInvisibleAfterLogin;
    String username;

    public AuthCallback(Label label_error, Fieldset fieldSetVisibleAfterLogin, Fieldset fieldSetInvisibleAfterLogin) {
        this.label_error = label_error;
        this.fieldSetVisibleAfterLogin = fieldSetVisibleAfterLogin;
        this.fieldSetInvisibleAfterLogin = fieldSetInvisibleAfterLogin;
        for (Widget widget : fieldSetVisibleAfterLogin) {
            if (widget.getTitle().equals("label_welcome")) this.label_welcome = (ControlLabel) widget;
        }
    }

    public void onSuccess(String result) {
        username = result;
        if ((result == null) && (label_error != null)) label_error.setText("User with such credentials wasn't found.");
        toggleWidgets();
    }

    public void onFailure(Throwable throwable) {
        label_error.setText("Failed to receive answer from server!");
    }

    private void toggleWidgets() {
        if (username != null) {
            label_welcome.getElement().setInnerHTML("Welcome, " + username + "!");
            for (Widget widget : fieldSetVisibleAfterLogin) widget.setVisible(true);
            for (Widget widget : fieldSetInvisibleAfterLogin) widget.setVisible(false);
        } else {
            for (Widget widget : fieldSetVisibleAfterLogin) widget.setVisible(false);
            for (Widget widget : fieldSetInvisibleAfterLogin) widget.setVisible(true);
        }
    }
}
