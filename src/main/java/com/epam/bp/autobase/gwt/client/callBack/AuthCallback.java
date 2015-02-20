package com.epam.bp.autobase.gwt.client.callBack;

import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.client.ui.Fieldset;
import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class AuthCallback implements AsyncCallback<String> {
    Label label_result;
    ControlLabel label_welcome;
    Fieldset fieldSetVisibleAfterLogin;
    Fieldset fieldSetInvisibleAfterLogin;
    String username;

    public AuthCallback(Label label_result, Fieldset fieldSetVisibleAfterLogin, Fieldset fieldSetInvisibleAfterLogin) {
        this.label_result = label_result;
        this.fieldSetVisibleAfterLogin = fieldSetVisibleAfterLogin;
        this.fieldSetInvisibleAfterLogin = fieldSetInvisibleAfterLogin;
        for (Widget widget : fieldSetVisibleAfterLogin) {
            if (widget.getTitle().equals("label_welcome")) this.label_welcome = (ControlLabel) widget;
        }
    }

    public void onSuccess(String result) {
        label_result.setText(result);
        username = result.substring(result.indexOf("'") + 1, result.lastIndexOf("'"));
        toggleWidgets();
    }

    public void onFailure(Throwable throwable) {
        label_result.setText("Failed to receive answer from server!");
    }

    private void toggleWidgets() {
        if ((label_result != null) && (label_result.getText().contains("logged-in"))) {
            label_welcome.getElement().setInnerHTML("Welcome, " + username + "!");
            for (Widget widget : fieldSetVisibleAfterLogin) widget.setVisible(true);
            for (Widget widget : fieldSetInvisibleAfterLogin) widget.setVisible(false);
        } else {
            for (Widget widget : fieldSetVisibleAfterLogin) widget.setVisible(false);
            for (Widget widget : fieldSetInvisibleAfterLogin) widget.setVisible(true);
        }
    }
}
