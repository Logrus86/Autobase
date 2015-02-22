package com.epam.bp.autobase.gwt.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("AutobaseGwtService")
public interface GenericRpcService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    String login(String username, String password);

    String logout();

    /**
     * Utility/Convenience class.
     * Use AutobaseGwtService.App.getInstance() to access static instance of AutobaseGwtServiceAsync
     */
    public static class App {
        private static GenericRpcServiceAsync ourInstance = GWT.create(GenericRpcService.class);

        public static synchronized GenericRpcServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
