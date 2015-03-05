package com.epam.bp.autobase.gwt.client.ui.template;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.rpc.FetchListToSelectCallback;
import com.epam.bp.autobase.gwt.client.rpc.FetchService;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.extras.select.client.ui.Select;

public class SearchForm extends Composite implements IsWidget {
    interface ThisViewUiBinder extends UiBinder<Widget, SearchForm> {
    }

    private static ThisViewUiBinder uiBinder = GWT.create(ThisViewUiBinder.class);
    private Presenter presenter;
    @UiField
    Select modelSelect;
    @UiField
    Button button;

    public SearchForm() {
        initWidget(uiBinder.createAndBindUi(this));
        FetchService.App.getInstance().fetchModels(new FetchListToSelectCallback(modelSelect));
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @UiHandler("button")
    public void onButtonClick(ClickEvent e) {
        modelSelect.getAllSelectedValues();
    }
}
