package com.epam.bp.autobase.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AutobaseGwtServiceAsync {
    // Sample interface method of remote interface
    void getMessage(String msg, AsyncCallback<String> async);
}
