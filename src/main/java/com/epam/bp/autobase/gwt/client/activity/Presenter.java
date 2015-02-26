package com.epam.bp.autobase.gwt.client.activity;

import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.google.gwt.place.shared.Place;

public interface Presenter {

    void goTo(Place place, UserDtoGwt user);
}
