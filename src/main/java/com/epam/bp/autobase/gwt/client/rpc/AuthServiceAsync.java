package com.epam.bp.autobase.gwt.client.rpc;

import com.epam.bp.autobase.model.dto.UserDto;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AuthServiceAsync {

    void login(String username, String password, AsyncCallback<UserDto> async);

    void logout(AsyncCallback<Void> async);

    void loginCheck(AsyncCallback<UserDto> async);
}
