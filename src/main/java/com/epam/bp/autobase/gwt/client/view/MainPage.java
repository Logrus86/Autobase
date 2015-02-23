package com.epam.bp.autobase.gwt.client.view;

import com.epam.bp.autobase.gwt.client.rpc.AuthCallback;
import com.epam.bp.autobase.gwt.client.rpc.RpcService;
import com.github.gwtbootstrap.client.ui.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Panel;


public class MainPage {
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    @UiField
    Panel root;
    @UiField
    Label label_loginResult;
    @UiField
    ControlLabel label_welcome;
    @UiField
    TextBox textBox_username;
    @UiField
    PasswordTextBox textBox_password;
    @UiField
    Fieldset fieldSetInvisibleAfterLogin;
    @UiField
    Fieldset fieldSetVisibleAfterLogin;

    public Label getLabel_loginResult() {
        return label_loginResult;
    }

    public Fieldset getFieldSetInvisibleAfterLogin() {
        return fieldSetInvisibleAfterLogin;
    }

    public Fieldset getFieldSetVisibleAfterLogin() {
        return fieldSetVisibleAfterLogin;
    }

    public MainPage() {
        uiBinder.createAndBindUi(this);
    }

    public Panel getElement() {
        return root;
    }

    @UiHandler("form_login")
    public void onFormLoginSubmit(Form.SubmitEvent e) {
        RpcService.App.getInstance().login(textBox_username.getValue(), textBox_password.getValue(), new AuthCallback(label_loginResult, fieldSetVisibleAfterLogin, fieldSetInvisibleAfterLogin));
    }

    @UiHandler("button_logout")
    public void onLogoutClick(ClickEvent e) {
        RpcService.App.getInstance().logout(new AuthCallback(null, fieldSetVisibleAfterLogin, fieldSetInvisibleAfterLogin));
    }

    interface MyUiBinder extends UiBinder<Panel, MainPage> {
    }
}
