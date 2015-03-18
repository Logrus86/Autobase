package com.epam.bp.autobase.gwt.client;

import com.epam.bp.autobase.gwt.client.mvp.AppActivityMapper;
import com.epam.bp.autobase.gwt.client.mvp.AppPlaceHistoryMapper;
import com.epam.bp.autobase.gwt.client.place.Index;
import com.epam.bp.autobase.gwt.client.rpc.LoginCheckCallback;
import com.epam.bp.autobase.gwt.server.AuthServiceImpl;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.UmbrellaException;

public class AutobaseApp implements EntryPoint {

    private Place defaultPlace = new Index();
    private SimplePanel appWidget = new SimplePanel();


    @Override
    public void onModuleLoad() {
        GWT.setUncaughtExceptionHandler(
                new GWT.UncaughtExceptionHandler() {
                    public void onUncaughtException(Throwable e) {
                        if (e instanceof UmbrellaException) {
                            UmbrellaException ue = (UmbrellaException) e;
                            if (ue.getCauses().size() == 1) {
                                Window.alert("Uncaught unwrapped exception! " + ue.getCauses().iterator().next().getMessage());
                            }
                        } else Window.alert("Uncaught exception! " + e.getMessage());
                    }
                }
        );

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

        AuthServiceImpl.App.getInstance().loginCheck(new LoginCheckCallback(viewFactory, new Index()));
    }
}
