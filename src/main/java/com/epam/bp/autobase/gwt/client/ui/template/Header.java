package com.epam.bp.autobase.gwt.client.ui.template;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.place.Index;
import com.epam.bp.autobase.gwt.client.rpc.AuthService;
import com.epam.bp.autobase.gwt.client.rpc.LoginCallback;
import com.epam.bp.autobase.gwt.client.rpc.LogoutCallback;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.base.form.AbstractForm;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimePicker;

public class Header extends Composite implements IsWidget {
    interface ThisViewUiBinder extends UiBinder<Widget, Header> {}
    private static ThisViewUiBinder uiBinder = GWT.create(ThisViewUiBinder.class);
    private Presenter listener;
    public void setPresenter(Presenter presenter) {
        this.listener = presenter;
    }
    @UiField
    Form form_login;
    @UiField
    Form form_logout;
    @UiField
    Label label_welcome;
    @UiField
    Label widget_loginResult;
    @UiField
    FormGroup loginInputs;
    @UiField
    TextBox textBox_username;
    @UiField
    Input textBox_password;
    @UiField
    Button button_register;
    @UiField
    Modal modalRegistration;
    @UiField
    Button button_modalRegister;
    @UiField
    Button button_modalCancel;
    @UiField
    TextBox input_firstname;
    @UiField
    DateTimePicker input_dob;

    public Header() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setLoggedIn(String username) {
        form_login.setVisible(false);
        form_logout.setVisible(true);
        label_welcome.setText("Welcome, "+username+" !");
    }

    public void setLoggedOut() {
        form_login.setVisible(true);
        form_logout.setVisible(false);
    }

    @UiHandler("form_login")
    public void onFormLoginSubmit(AbstractForm.SubmitEvent e) {
        AuthService.App.getInstance().login(textBox_username.getText(), textBox_password.getText(), new LoginCallback(listener, loginInputs, widget_loginResult));
    }

    @UiHandler("logo")
    public void onImageClick(ClickEvent e) {
        listener.goTo(new Index());
    }

    @UiHandler("button_logout")
    public void onLogoutClick(ClickEvent e) {
        AuthService.App.getInstance().logout(new LogoutCallback(listener));
    }

    @UiHandler("button_register")
    public void onButtonRegisterClick(ClickEvent e) {
        modalRegistration.show();
        input_dob.setValue(null);
    }

    @UiHandler("button_modalCancel")
    public void onButtonModalCancelClick(ClickEvent e) {
        modalRegistration.hide();
    }

    @UiHandler("input_firstname")
    public void onInputFirstnameChanged(ChangeEvent e) {

    }
}
