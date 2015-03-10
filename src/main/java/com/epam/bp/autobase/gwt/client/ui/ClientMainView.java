package com.epam.bp.autobase.gwt.client.ui;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.ui.template.Header;
import com.epam.bp.autobase.gwt.client.ui.template.SearchForm;
import com.epam.bp.autobase.model.dto.UserDto;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Panel;

public class ClientMainView extends Composite implements IsWidget {
    private static ThisViewUiBinder uiBinder = GWT.create(ThisViewUiBinder.class);
    @UiField
    Header header;
    @UiField
    SearchForm searchForm;
    @UiField
    Panel registeredForm;
    @UiField
    Label labelFirstname;
    @UiField
    Label labelLastname;
    @UiField
    Label labelDob;
    @UiField
    Label labelUsername;
    @UiField
    Label labelPassword;
    @UiField
    Label labelEmail;
    private Presenter presenter;
    private String name;
    private UserDto user;

    public ClientMainView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        switch (name) {
            case "main": {
                searchForm.setVisible(true);
                registeredForm.setVisible(false);
                break;
            }
            case "registered": {
                searchForm.setVisible(false);
                labelFirstname.setText(user.getFirstname());
                labelLastname.setText(user.getLastname());
                labelDob.setText(user.getDob());
                labelUsername.setText(user.getUsername());
                labelPassword.setText(user.getPassword() /*"********"*/);
                labelEmail.setText(user.getEmail());
                registeredForm.setVisible(true);
                break;
            }
        }
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
        header.setPresenter(presenter);
        searchForm.setPresenter(presenter);
    }

    public UserDto getUser() {
        return user;
    }

    public ClientMainView setUser(UserDto user) {
        if (user.getFirstname() == null) header.setLoggedIn(user.getUsername());
        else header.setLoggedIn(user.getFirstname());
        this.user = user;
        return this;
    }

    interface ThisViewUiBinder extends UiBinder<Widget, ClientMainView> {
    }

}
