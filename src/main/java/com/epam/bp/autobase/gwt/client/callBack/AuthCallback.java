package com.epam.bp.autobase.gwt.client.callBack;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class AuthCallback implements AsyncCallback<String> {
    Label label;
    Button buttonLogout;
    Widget[] widgets;

    public AuthCallback(Label labelForResultString, Button buttonLogout, Widget... widgetsToToggleVisible) {
        this.widgets = widgetsToToggleVisible;
        this.label = labelForResultString;
        this.buttonLogout = buttonLogout;
    }

    public void onSuccess(String result) {
        label.setText(result);
        toggleWidgetsVisible();
    }

    public void onFailure(Throwable throwable) {
        label.setText("Failed to receive answer from server!");
    }

    private void toggleWidgetsVisible() {
        if ((label != null) && (label.getText().contains("logged-in"))) {
            buttonLogout.setVisible(true);
            for (Widget widget : widgets) widget.setVisible(false);
        } else {
            buttonLogout.setVisible(false);
            for (Widget widget : widgets) widget.setVisible(true);
        }
    }
}
