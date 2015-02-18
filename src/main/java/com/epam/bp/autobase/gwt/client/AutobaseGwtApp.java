package com.epam.bp.autobase.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class AutobaseGwtApp implements EntryPoint {

    @Override
    public void onModuleLoad() {
        RootPanel.get().add(new AutobaseGwtClient().getElement());
    }
}
