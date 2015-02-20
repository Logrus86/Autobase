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
import com.google.gwt.user.client.ui.Widget;


public class MainPage {
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    @UiField
    Panel root;
    @UiField
    Label label_loginResult;
    @UiField
    TextBox textBox_username;
    @UiField
    PasswordTextBox textBox_password;
    @UiField
    SubmitButton button_login;
    @UiField
    Button button_register;
    @UiField
    ControlLabel label_welcome;
    @UiField
    Button button_cabinet;
    @UiField
    Button button_logout;

    public MainPage() {
        uiBinder.createAndBindUi(this);
    }

    public Panel getElement() {
        return root;
    }

    @UiHandler("form_login")
    public void onFormLoginSubmit(Form.SubmitEvent e) {
        GenericRpcService.App.getInstance().login(textBox_username.getValue(), textBox_password.getValue(), new AuthCallback(label_loginResult, label_welcome, visibleAfterLoginWidgets(), invisibleAfterLoginWidgets()));
    }

    @UiHandler("button_logout")
    public void onLogoutClick(ClickEvent e) {
        GenericRpcService.App.getInstance().logout(new AuthCallback(label_loginResult, label_welcome,
                visibleAfterLoginWidgets(), invisibleAfterLoginWidgets()));
    }

    private Widget[] visibleAfterLoginWidgets() {
        Widget[] result = new Widget[3];
        result[0] = button_logout;
        result[1] = label_welcome;
        result[2] = button_cabinet;
        return result;
    }

    private Widget[] invisibleAfterLoginWidgets() {
        Widget[] result = new Widget[4];
        result[0] = button_register;
        result[1] = textBox_username;
        result[2] = textBox_password;
        result[3] = button_login;
        return result;
    }

    interface MyUiBinder extends UiBinder<Panel, MainPage> {
    }
}
