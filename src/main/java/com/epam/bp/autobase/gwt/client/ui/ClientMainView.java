package com.epam.bp.autobase.gwt.client.ui;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.rpc.AuthService;
import com.epam.bp.autobase.gwt.client.rpc.LogoutCallback;
import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ClientMainView extends Composite implements IsWidget {
    private static ThisViewUiBinder uiBinder = GWT.create(ThisViewUiBinder.class);
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    interface ThisViewUiBinder extends UiBinder<Widget, ClientMainView>
    {
    }
    private Presenter listener;
    private UserDtoGwt user;

    @UiField
    ControlLabel label_welcome;

    public ClientMainView(UserDtoGwt user) {
        initWidget(uiBinder.createAndBindUi(this));
        this.user = user;
        label_welcome.getElement().setInnerHTML("Welcome, "+user.getFirstname()+" !");
    }

    public void setPresenter(Presenter presenter) {
        this.listener = presenter;
    }



    @UiHandler("button_logout")
    public void onLogoutClick(ClickEvent e) {
        AuthService.App.getInstance().logout(new LogoutCallback(listener));
    }

}
