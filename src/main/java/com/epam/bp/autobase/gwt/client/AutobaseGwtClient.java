package com.epam.bp.autobase.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

public class AutobaseGwtClient {
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    @UiField
    Panel root;
    @UiField
    InputElement name;
    @UiField
    Button button;
    @UiField
    Label label;

    public AutobaseGwtClient() {
        uiBinder.createAndBindUi(this);
        button.getElement().setAttribute("name", "button");
    }

    @UiHandler("button")
    public void onButtonClick(ClickEvent e) {
        AutobaseGwtService.App.getInstance().getMessage(name.getValue(), new MyAsyncCallback(label));
    }

    public Panel getElement() {
        return root;
    }

    interface MyUiBinder extends UiBinder<Panel, AutobaseGwtClient> {
    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
