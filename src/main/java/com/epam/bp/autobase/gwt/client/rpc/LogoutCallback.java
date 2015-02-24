package com.epam.bp.autobase.gwt.client.rpc;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.place.Index;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LogoutCallback implements AsyncCallback<Void> {
    Presenter listener;

    public LogoutCallback(Presenter listener) {
        this.listener = listener;
    }

    @Override
    public void onFailure(Throwable caught) {

    }

    @Override
    public void onSuccess(Void result) {
        listener.goTo(new Index(), null);
    }
}
