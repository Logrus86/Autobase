package com.epam.bp.autobase.gwt.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("AutobaseGwtService")
public interface RpcService extends RemoteService {

    String login(String username, String password);

    String logout();

    String loginCheck();

    public static class App {
        private static RpcServiceAsync ourInstance = GWT.create(RpcService.class);

        public static synchronized RpcServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
