package com.epam.bp.autobase.gwt.client;


import com.epam.bp.autobase.gwt.client.ui.ClientMainView;
import com.epam.bp.autobase.gwt.client.ui.IndexView;
import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public class ViewFactory {

    private static final EventBus eventBus = new SimpleEventBus();
    private static final PlaceController placeController = new PlaceController(eventBus);
    private static final IndexView indexView = new IndexView();
    private static final ClientMainView clientMainView = new ClientMainView();
    private static UserDtoGwt user = null;

    public UserDtoGwt getUser() {
        return user;
    }

    public void setUser(UserDtoGwt user) {
        ViewFactory.user = user;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public PlaceController getPlaceController() {
        return placeController;
    }

    public IndexView getIndexView() {
        return indexView;
    }

    public ClientMainView getClientMainView() {
        return clientMainView.setUser(user);
    }
}
