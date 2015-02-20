package com.epam.bp.autobase.gwt.client.callBack;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class AuthCallback implements AsyncCallback<String> {
    Label labelLogin;
    ControlLabel labelWelcome;
    Button buttonLogout;
    Widget[] widgets;
    String username;

    public AuthCallback(Label labelForResultString, ControlLabel labelWelcome, Button buttonLogout, Widget... widgetsToToggleVisibleTogether) {
        this.widgets = widgetsToToggleVisibleTogether;
        this.labelLogin = labelForResultString;
        this.labelWelcome = labelWelcome;
        this.buttonLogout = buttonLogout;
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
            buttonLogout.setVisible(true);
            labelWelcome.setVisible(true);
            labelWelcome.getElement().setInnerHTML("Welcome, " + username + "!");
            for (Widget widget : widgets) widget.setVisible(false);
        } else {
            buttonLogout.setVisible(false);
            labelWelcome.setVisible(false);
            for (Widget widget : widgets) widget.setVisible(true);
        }
    }
}
