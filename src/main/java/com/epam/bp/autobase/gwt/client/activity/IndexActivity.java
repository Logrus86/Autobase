package com.epam.bp.autobase.gwt.client.activity;

import com.epam.bp.autobase.gwt.client.ViewFactory;
import com.epam.bp.autobase.gwt.client.place.Client;
import com.epam.bp.autobase.gwt.client.place.Index;
import com.epam.bp.autobase.gwt.client.rpc.LoginCheckCallback;
import com.epam.bp.autobase.gwt.client.ui.IndexView;
import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.epam.bp.autobase.gwt.server.AuthServiceImpl;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class IndexActivity extends AbstractActivity implements Presenter {
    private ViewFactory viewFactory;

    public IndexActivity(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        IndexView view = viewFactory.getIndexView();
        view.setPresenter(this);
        panel.setWidget(view.asWidget());
    }

    @Override
    public void goTo(Place place) {
        AuthServiceImpl.App.getInstance().loginCheck(new LoginCheckCallback(viewFactory, place));
    }

    @Override
    public void goTo(Place place, UserDtoGwt user) {
        viewFactory.setUser(user);
        if (user != null) viewFactory.getPlaceController().goTo(new Client("main"));
        else viewFactory.getPlaceController().goTo(new Index());
    }
}
