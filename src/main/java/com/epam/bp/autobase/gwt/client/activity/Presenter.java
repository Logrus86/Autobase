package com.epam.bp.autobase.gwt.client.activity;

import com.epam.bp.autobase.model.dto.UserDto;
import com.google.gwt.place.shared.Place;

public interface Presenter {

    void goTo(Place place);

    void goTo(Place place, UserDto user);
}
