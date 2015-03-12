package com.epam.bp.autobase.gwt.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class RegisterEvent extends GwtEvent<RegisterEventHandler> {
    public static GwtEvent.Type<RegisterEventHandler> TYPE = new GwtEvent.Type<>();

    @Override
    public GwtEvent.Type<RegisterEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RegisterEventHandler handler) {
        handler.onRegister(this);
    }
}
