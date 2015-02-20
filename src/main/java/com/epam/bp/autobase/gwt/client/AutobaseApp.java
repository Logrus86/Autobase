package com.epam.bp.autobase.gwt.client;

import com.epam.bp.autobase.gwt.client.view.MainPage;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class AutobaseApp implements EntryPoint {

    @Override
    public void onModuleLoad() {
        RootPanel.get().add(new MainPage().getElement());
    }
}
