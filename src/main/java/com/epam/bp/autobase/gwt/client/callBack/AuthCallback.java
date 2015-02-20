package com.epam.bp.autobase.gwt.client.callBack;

import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class AuthCallback implements AsyncCallback<String> {
    Label labelLogin;
    ControlLabel label_welcome;
    Widget[] visibleAfterLoginWidgets;
    Widget[] invisibleAfterLoginWidgets;
    String username;

    public AuthCallback(Label labelLogin, ControlLabel label_welcome, Widget[] visibleAfterLoginWidgets, Widget[] invisibleAfterLoginWidgets) {
        this.labelLogin = labelLogin;
        this.label_welcome = label_welcome;
        this.visibleAfterLoginWidgets = visibleAfterLoginWidgets;
        this.invisibleAfterLoginWidgets = invisibleAfterLoginWidgets;
    }

    public void onSuccess(String result) {
        labelLogin.setText(result);
        username = result.substring(result.indexOf("'") + 1, result.lastIndexOf("'"));
        toggleWidgets();
    }

    public void onFailure(Throwable throwable) {
        labelLogin.setText("Failed to receive answer from server!");
    }

    private void toggleWidgets() {
        if ((labelLogin != null) && (labelLogin.getText().contains("logged-in"))) {
            label_welcome.getElement().setInnerHTML("Welcome, " + username + "!");
            for (Widget widget : visibleAfterLoginWidgets) widget.setVisible(true);
            for (Widget widget : invisibleAfterLoginWidgets) widget.setVisible(false);
        } else {
            for (Widget widget : visibleAfterLoginWidgets) widget.setVisible(false);
            for (Widget widget : invisibleAfterLoginWidgets) widget.setVisible(true);
        }
    }
}
