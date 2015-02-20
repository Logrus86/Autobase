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
    Label label_loginResult;
    @UiField
    TextBox textBox_username;
    @UiField
    PasswordTextBox textBox_password;
    @UiField
    Fieldset fieldSetInvisibleAfterLogin;
    @UiField
    Fieldset fieldSetVisibleAfterLogin;

    public MainPage() {
        uiBinder.createAndBindUi(this);
    }

    public Panel getElement() {
        return root;
    }

    @UiHandler("form_login")
    public void onFormLoginSubmit(Form.SubmitEvent e) {
        GenericRpcService.App.getInstance().login(textBox_username.getValue(), textBox_password.getValue(), new AuthCallback(label_loginResult, fieldSetVisibleAfterLogin, fieldSetInvisibleAfterLogin));
    }

    @UiHandler("button_logout")
    public void onLogoutClick(ClickEvent e) {
        GenericRpcService.App.getInstance().logout(new AuthCallback(label_loginResult, fieldSetVisibleAfterLogin, fieldSetInvisibleAfterLogin));
    }

    interface MyUiBinder extends UiBinder<Panel, MainPage> {
    }
}
