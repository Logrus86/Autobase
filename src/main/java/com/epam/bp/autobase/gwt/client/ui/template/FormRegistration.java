package com.epam.bp.autobase.gwt.client.ui.template;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.place.Client;
import com.epam.bp.autobase.gwt.client.rpc.RegisterService;
import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.model.entity.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimePicker;

import javax.inject.Inject;

public class FormRegistration extends Composite implements Editor<User> {
    @UiField
    Modal modal_registration;
    @UiField
    HelpBlock help_registration;
    @UiField
    Button button_modalRegister;
    @UiField
    Button button_modalCancel;
    @UiField
    TextBox input_modalFirstname;
    @UiField
    TextBox input_modalLastname;
    @UiField
    DateTimePicker input_modalDob;
    @UiField
    TextBox input_modalUsername;
    @UiField
    Input input_modalPassword;
    @UiField
    Input input_modalPasswordRepeat;
    @UiField
    Input input_modalEmail;

    @Inject
    public FormRegistration() {
        initWidget(BINDER.createAndBindUi(this));
    }

    public Presenter getListener() {
        return listener;
    }

    public void setListener(Presenter listener) {
        this.listener = listener;
    }

    private Presenter listener;

    public void show() {
        modal_registration.show();
    }

    public interface Binder extends UiBinder<Form, FormRegistration> {
    }

    private static final Binder BINDER = GWT.create(Binder.class);

    @UiHandler("button_modalRegister")
    public void onButtonModalRegisterClick(ClickEvent e) {
        UserDto userDto = new UserDto()
                .setFirstname(input_modalFirstname.getText())
                .setLastname(input_modalLastname.getText())
                .setDob(input_modalDob.getTextBox().getText())
                .setUsername(input_modalUsername.getText())
                .setPassword(input_modalPassword.getText())
                .setPassword_repeat(input_modalPasswordRepeat.getText())
                .setEmail(input_modalEmail.getText())
                .setBalance("0")
                .setRole(User.Role.CLIENT);
        RegisterService.App.getInstance().register(userDto, new AsyncCallback<UserDto>() {
            @Override
            public void onFailure(Throwable caught) {
                help_registration.setText("Server-side failure to register user");
            }

            @Override
            public void onSuccess(UserDto result) {
                if (result != null) {
                    modal_registration.hide();
                    listener.goTo(new Client("registered"), result);
                } else help_registration.setText("User registration isn't completed");
            }
        });
    }

    @UiHandler("button_modalCancel")
    public void onButtonModalCancelClick(ClickEvent e) {
        modal_registration.hide();
    }

    @UiHandler("input_modalFirstname")
    public void onInputFirstnameChanged(ChangeEvent e) {
    }
}
