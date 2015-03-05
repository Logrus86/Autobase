package com.epam.bp.autobase.gwt.client.ui;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.ui.template.Header;
import com.epam.bp.autobase.gwt.client.ui.template.SearchForm;
import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ClientMainView extends Composite implements IsWidget {
    private static ThisViewUiBinder uiBinder = GWT.create(ThisViewUiBinder.class);
    @UiField
    Header header;
    @UiField
    SearchForm searchForm;
    private Presenter presenter;
    private String name;
    private UserDtoGwt user;

    public ClientMainView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
        header.setPresenter(presenter);
        searchForm.setPresenter(presenter);
    }

    public UserDtoGwt getUser() {
        return user;
    }

    public ClientMainView setUser(UserDtoGwt user) {
        if (user.getFirstname() == null) header.setLoggedIn(user.getUsername());
        else header.setLoggedIn(user.getFirstname());
        this.user = user;
        return this;
    }

    interface ThisViewUiBinder extends UiBinder<Widget, ClientMainView> {
    }

}
