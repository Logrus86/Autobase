package com.epam.bp.autobase.gwt.client.ui.template;

import com.epam.bp.autobase.gwt.client.activity.IndexActivity;
import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.model.entity.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.form.error.BasicEditorError;
import org.gwtbootstrap3.client.ui.form.validator.Validator;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimePicker;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
    private Presenter presenter;

    @Inject
    public FormRegistration() {
        initWidget(BINDER.createAndBindUi(this));
        input_modalPasswordRepeat.addValidator(new Validator<String>() {

            @Override
            public int getPriority() {
                return 0;
            }

            @Override
            public List<EditorError> validate(Editor<String> editor, String value) {
                List<EditorError> result = new ArrayList<>();
                if (!input_modalPasswordRepeat.getText().equals(input_modalPassword.getText())) {
                    result.add(new BasicEditorError(editor, value, "Passwords are not equal."));
                }
                return result;
            }
        });
    }

    public Button getButton_modalRegister() {
        return button_modalRegister;
    }

    public TextBox getInput_modalFirstname() {
        return input_modalFirstname;
    }

    public TextBox getInput_modalLastname() {
        return input_modalLastname;
    }

    public DateTimePicker getInput_modalDob() {
        return input_modalDob;
    }

    public TextBox getInput_modalUsername() {
        return input_modalUsername;
    }

    public Input getInput_modalPassword() {
        return input_modalPassword;
    }

    public Input getInput_modalPasswordRepeat() {
        return input_modalPasswordRepeat;
    }

    public Input getInput_modalEmail() {
        return input_modalEmail;
    }

    public HelpBlock getHelp_registration() {
        return help_registration;
    }

    public Modal getModal_registration() {
        return modal_registration;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void show() {
        modal_registration.show();
    }

    public interface Binder extends UiBinder<Form, FormRegistration> {
    }

    private static final Binder BINDER = GWT.create(Binder.class);

    @UiHandler("button_modalRegister")
    public void onButtonModalRegisterClick(ClickEvent e) {
        if (presenter != null) {
            ((IndexActivity) presenter).onModalRegisterButtonClicked();
        }
    }

    @UiHandler("button_modalCancel")
    public void onButtonModalCancelClick(ClickEvent e) {
        modal_registration.hide();
    }

}
