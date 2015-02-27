package com.epam.bp.autobase.gwt.client;

import com.epam.bp.autobase.gwt.client.mvp.AppActivityMapper;
import com.epam.bp.autobase.gwt.client.mvp.AppPlaceHistoryMapper;
import com.epam.bp.autobase.gwt.client.place.Index;
import com.epam.bp.autobase.gwt.client.rpc.LoginCheckCallback;
import com.epam.bp.autobase.gwt.server.AuthServiceImpl;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

public class AutobaseApp implements EntryPoint {

    private Place defaultPlace = new Index();
    private SimplePanel appWidget = new SimplePanel();


    @Override
    public void onModuleLoad() {
        ViewFactory viewFactory = GWT.create(ViewFactory.class);
        EventBus eventBus = viewFactory.getEventBus();
        PlaceController placeController = viewFactory.getPlaceController();

        // Start ActivityManager for the main widget with our ActivityMapper
        ActivityMapper activityMapper = new AppActivityMapper(viewFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(appWidget);

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        RootPanel.get().add(appWidget);
        // Goes to place represented on URL or default place
        //historyHandler.handleCurrentHistory();

        AuthServiceImpl.App.getInstance().loginCheck(new LoginCheckCallback(viewFactory, new Index()));
    }
}
