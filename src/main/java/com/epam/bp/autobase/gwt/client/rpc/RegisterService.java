package com.epam.bp.autobase.gwt.client.rpc;

import com.epam.bp.autobase.model.dto.UserDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("RegisterService")
public interface RegisterService extends RemoteService {

    UserDto register(UserDto userDto);

    static class App {
        private static RegisterServiceAsync ourInstance = GWT.create(RegisterService.class);

        public static synchronized RegisterServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
