package com.epam.bp.autobase.gwt.client;

import com.epam.bp.autobase.gwt.client.rpc.AuthCallback;
import com.epam.bp.autobase.gwt.client.rpc.RpcService;
import com.epam.bp.autobase.gwt.client.view.MainPage;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class AutobaseApp implements EntryPoint {
    MainPage mainPage = new MainPage();

    @Override
    public void onModuleLoad() {
        RpcService.App.getInstance().loginCheck(new AuthCallback(mainPage.getLabel_loginResult(), mainPage.getFieldSetVisibleAfterLogin(), mainPage.getFieldSetInvisibleAfterLogin()));
        showApp();
    }

    private void showApp() {
        RootPanel.get().clear();
        RootPanel.get().add(mainPage.getElement());
    }
}
