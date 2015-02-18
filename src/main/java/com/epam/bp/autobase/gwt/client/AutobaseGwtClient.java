package com.epam.bp.autobase.gwt.client;

import com.github.gwtbootstrap.client.ui.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Panel;

public class AutobaseGwtClient {
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    @UiField
    Panel root;
    @UiField
    Label label_login_error;
    @UiField
    TextBox username;
    @UiField
    PasswordTextBox password;
    @UiField
    Button button_logout;

    public AutobaseGwtClient() {
        uiBinder.createAndBindUi(this);
    }

    @UiHandler("form_login")
    public void onFormLoginSubmit(Form.SubmitEvent e) {
        AutobaseGwtService.App.getInstance().login(username.getValue(), password.getValue(), new MyAsyncCallback(label_login_error));
    }

    @UiHandler("button_logout")
    public void onLogoutClick(ClickEvent e) {
        AutobaseGwtService.App.getInstance().logout(new MyAsyncCallback(label_login_error));
    }

    public Panel getElement() {
        return root;
    }

    interface MyUiBinder extends UiBinder<Panel, AutobaseGwtClient> {
    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
