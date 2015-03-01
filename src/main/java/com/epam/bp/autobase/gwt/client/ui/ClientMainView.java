package com.epam.bp.autobase.gwt.client.ui;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.ui.template.Header;
import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ClientMainView extends Composite implements IsWidget {
    interface ThisViewUiBinder extends UiBinder<Widget, ClientMainView> {}
    private static ThisViewUiBinder uiBinder = GWT.create(ThisViewUiBinder.class);

    @UiField
    Header header;

    private String name;
    private UserDtoGwt user;


    public ClientMainView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public ClientMainView setUser(UserDtoGwt user) {
        header.setLoggedIn(user.getFirstname());
        this.user = user;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPresenter(Presenter presenter) {
        header.setPresenter(presenter);
    }


}
