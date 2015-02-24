package com.epam.bp.autobase.gwt.client.rpc;

import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AuthServiceAsync {

    void login(String username, String password, AsyncCallback<UserDtoGwt> async);

    void logout(AsyncCallback<Void> async);

    void loginCheck(AsyncCallback<UserDtoGwt> async);
}
