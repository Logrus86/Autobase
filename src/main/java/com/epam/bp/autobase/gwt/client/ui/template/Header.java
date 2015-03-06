package com.epam.bp.autobase.gwt.client.ui.template;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.place.Client;
import com.epam.bp.autobase.gwt.client.place.Index;
import com.epam.bp.autobase.gwt.client.rpc.AuthService;
import com.epam.bp.autobase.model.dto.UserDto;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.constants.ValidationState;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimePicker;

public class Header extends Composite implements IsWidget {
    private static ThisViewUiBinder uiBinder = GWT.create(ThisViewUiBinder.class);
    @UiField
    Form form_login;
    @UiField
    Form form_logout;
    @UiField
    Button button_login;
    @UiField
    Label label_welcome;
    @UiField
    HelpBlock hb_loginResult;
    @UiField
    FormGroup fg_loginInputs;
    @UiField
    TextBox input_username;
    @UiField
    Input input_password;
    @UiField
    Button button_register;
    @UiField
    Modal modal_registration;
    @UiField
    Button button_modalRegister;
    @UiField
    Button button_modalCancel;
    @UiField
    TextBox input_firstname;
    @UiField
    DateTimePicker input_dob;
    private Presenter listener;

    public Header() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setPresenter(Presenter presenter) {
        this.listener = presenter;
    }

    public void setLoggedIn(String username) {
        form_login.setVisible(false);
        form_logout.setVisible(true);
        label_welcome.setText("Welcome, " + username + " !");
    }

    public void setLoggedOut() {
        form_login.setVisible(true);
        form_logout.setVisible(false);
    }

    @UiHandler("button_login")
    public void onButtonLoginClick(ClickEvent e) {
        submitLoginForm();
    }

    @UiHandler({"input_username", "input_password"})
    public void onPasswordInputEnterPressed(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) submitLoginForm();
    }

    private void submitLoginForm() {
        AuthService.App.getInstance().login(input_username.getText(), input_password.getText(),
                new AsyncCallback<UserDto>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        hb_loginResult.setText("Failed to receive answer from server: " + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(UserDto result) {
                        if (result != null) listener.goTo(new Client("main"), result);
                        else {
                            fg_loginInputs.setValidationState(ValidationState.ERROR);
                            hb_loginResult.setText("User with such credentials wasn't found");
                        }
                    }
                });
    }

    @UiHandler("logo")
    public void onImageClick(ClickEvent e) {
        listener.goTo(new Index());
    }

    @UiHandler("button_logout")
    public void onLogoutClick(ClickEvent e) {
        AuthService.App.getInstance().logout(new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                label_welcome.setText("Logout error on server side.");
            }

            @Override
            public void onSuccess(Void result) {
                listener.goTo(new Index());
            }
        });
    }

    @UiHandler("button_register")
    public void onButtonRegisterClick(ClickEvent e) {
        modal_registration.show();
        input_dob.setValue(null);
    }

    @UiHandler("button_modalCancel")
    public void onButtonModalCancelClick(ClickEvent e) {
        modal_registration.hide();
    }

    @UiHandler("input_firstname")
    public void onInputFirstnameChanged(ChangeEvent e) {

    }

    interface ThisViewUiBinder extends UiBinder<Widget, Header> {
    }
}
