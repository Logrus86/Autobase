package com.epam.bp.autobase.gwt.client.rpc;

import com.epam.bp.autobase.gwt.client.ViewFactory;
import com.epam.bp.autobase.gwt.client.place.Client;
import com.epam.bp.autobase.gwt.client.place.Index;
import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LoginCheckCallback implements AsyncCallback<UserDtoGwt> {

    ViewFactory viewFactory;
    Place place;

    public LoginCheckCallback(ViewFactory viewFactory, Place place) {
        this.viewFactory = viewFactory;
        this.place = place;
    }

    @Override
    public void onFailure(Throwable caught) {
        viewFactory.setUser(null);
    }

    @Override
    public void onSuccess(UserDtoGwt user) {
        viewFactory.setUser(user);
        if (user != null) {
            if (place instanceof Index) viewFactory.getPlaceController().goTo(new Client("main"));
            else viewFactory.getPlaceController().goTo(place);
        } else viewFactory.getPlaceController().goTo(new Index());
    }
}
