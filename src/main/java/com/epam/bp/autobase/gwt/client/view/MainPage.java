package com.epam.bp.autobase.gwt.client.view;

import com.epam.bp.autobase.gwt.client.GenericRpcService;
import com.epam.bp.autobase.gwt.client.callBack.AuthCallback;
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
    Label labelLogin;
    @UiField
    TextBox textBox_username;
    @UiField
    PasswordTextBox textBox_password;
    @UiField
    SubmitButton button_login;
    @UiField
    Button button_logout;
    @UiField
    Button button_register;

    public MainPage() {
        uiBinder.createAndBindUi(this);
    }

    public Panel getElement() {
        return root;
    }

    @UiHandler("form_login")
    public void onFormLoginSubmit(Form.SubmitEvent e) {
        GenericRpcService.App.getInstance().login(textBox_username.getValue(), textBox_password.getValue(), new AuthCallback(labelLogin, button_logout, textBox_username, textBox_password, button_login, button_register));
    }

    @UiHandler("button_logout")
    public void onLogoutClick(ClickEvent e) {
        GenericRpcService.App.getInstance().logout(new AuthCallback(labelLogin, button_logout, textBox_username, textBox_password, button_login, button_register));
    }

    interface MyUiBinder extends UiBinder<Panel, MainPage> {
    }
}
