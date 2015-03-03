package com.epam.bp.autobase.gwt.client.ui;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.ui.template.Header;
import com.epam.bp.autobase.gwt.client.ui.template.SearchForm;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;


public class IndexView extends Composite implements IsWidget {
    interface ThisViewUiBinder extends UiBinder<Widget, IndexView> {}
    private static ThisViewUiBinder uiBinder = GWT.create(ThisViewUiBinder.class);
    private Presenter presenter;
    @UiField
    Header header;
    @UiField
    SearchForm searchForm;

    public IndexView() {
        initWidget(uiBinder.createAndBindUi(this));
        header.setLoggedOut();
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
        header.setPresenter(presenter);
        searchForm.setPresenter(presenter);
    }
}
