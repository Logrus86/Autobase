package com.epam.bp.autobase.gwt.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GenericRpcServiceAsync {
    // Sample interface method of remote interface
    void getMessage(String msg, AsyncCallback<String> async);

    void login(String username, String password, AsyncCallback<String> async);

    void logout(AsyncCallback<String> async);

}
