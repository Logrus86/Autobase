package com.epam.bp.autobase.gwt.client.rpc;

import com.epam.bp.autobase.model.dto.UserDto;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RegisterServiceAsync {

    void register(UserDto userDto, AsyncCallback<UserDto> async);
}
