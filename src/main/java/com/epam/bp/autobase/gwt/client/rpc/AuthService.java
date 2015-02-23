package com.epam.bp.autobase.gwt.client.rpc;

import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("AuthService")
public interface AuthService extends RemoteService {

    UserDtoGwt login(String username, String password);

    UserDtoGwt logout();

    UserDtoGwt loginCheck();

    public static class App {
        private static AuthServiceAsync ourInstance = GWT.create(AuthService.class);

        public static synchronized AuthServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
