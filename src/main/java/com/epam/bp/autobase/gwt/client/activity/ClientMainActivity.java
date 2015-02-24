package com.epam.bp.autobase.gwt.client.activity;

import com.epam.bp.autobase.gwt.client.ViewFactory;
import com.epam.bp.autobase.gwt.client.place.Client;
import com.epam.bp.autobase.gwt.client.ui.ClientMainView;
import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ClientMainActivity extends AbstractActivity implements Presenter {
    private ViewFactory viewFactory;
    private String name;

    public ClientMainActivity(Client place, ViewFactory viewFactory) {
        this.name = place.getPlaceName();
        this.viewFactory = viewFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        ClientMainView view = viewFactory.getClientMainView();
        view.setName(name);
        view.setPresenter(this);
        panel.setWidget(view.asWidget());
    }

    @Override
    public void goTo(Place place, UserDtoGwt user) {
        viewFactory.setUser(user);
        viewFactory.getPlaceController().goTo(place);
    }
}
