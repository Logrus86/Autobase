package com.epam.bp.autobase.gwt.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RpcServiceAsync {

    void login(String username, String password, AsyncCallback<String> async);

    void logout(AsyncCallback<String> async);

    void loginCheck(AsyncCallback<String> async);
}
