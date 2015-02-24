package com.epam.bp.autobase.gwt.client.mvp;

import com.epam.bp.autobase.gwt.client.ViewFactory;
import com.epam.bp.autobase.gwt.client.activity.ClientMainActivity;
import com.epam.bp.autobase.gwt.client.activity.IndexActivity;
import com.epam.bp.autobase.gwt.client.place.Client;
import com.epam.bp.autobase.gwt.client.place.Index;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper implements ActivityMapper {

    private ViewFactory viewFactory;

    public AppActivityMapper(ViewFactory viewFactory) {
        super();
        this.viewFactory = viewFactory;
    }

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof Index) return new IndexActivity(viewFactory);
        else if (place instanceof Client) return new ClientMainActivity((Client) place, viewFactory);

        return null;
    }
}
