package com.epam.bp.autobase.gwt.client.ui;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.place.Index;
import com.epam.bp.autobase.gwt.client.rpc.AuthService;
import com.epam.bp.autobase.gwt.client.rpc.LoginCallback;
import com.github.gwtbootstrap.client.ui.*;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;


public class IndexView extends Composite implements IsWidget {
    private static ThisViewUiBinder uiBinder = GWT.create(ThisViewUiBinder.class);
    @UiField
    Form form_login;
    @UiField
    Column label_loginResult;
    @UiField
    ControlGroup loginInputs;
    @UiField
    TextBox textBox_username;
    @UiField
    PasswordTextBox textBox_password;
    private Presenter listener;
    public IndexView() {
        initWidget(uiBinder.createAndBindUi(this));
        form_login.getElement().setAttribute("id", "loginForm");
    }

    public void setPresenter(Presenter presenter) {
        this.listener = presenter;
    }

    @UiHandler("form_login")
    public void onFormLoginSubmit(Form.SubmitEvent e) {
        AuthService.App.getInstance().login(textBox_username.getValue(), textBox_password.getValue(),new LoginCallback(listener, loginInputs, label_loginResult));
    }

    @UiHandler("logo")
    public void onImageClick(ClickEvent e) {
        listener.goTo(new Index());
    }

    interface ThisViewUiBinder extends UiBinder<Widget, IndexView> {
    }
}
