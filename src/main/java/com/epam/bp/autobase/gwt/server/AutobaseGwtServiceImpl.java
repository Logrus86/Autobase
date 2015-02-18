package com.epam.bp.autobase.gwt.server;

import com.epam.bp.autobase.gwt.client.AutobaseGwtService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AutobaseGwtServiceImpl extends RemoteServiceServlet implements AutobaseGwtService {

    @Override
    public String getMessage(String msg) {
        return "Client said: " + msg;
    }
}
