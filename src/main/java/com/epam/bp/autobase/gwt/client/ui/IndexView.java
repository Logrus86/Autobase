package com.epam.bp.autobase.gwt.client.ui;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.ui.template.Header;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;


public class IndexView extends Composite implements IsWidget {
    interface ThisViewUiBinder extends UiBinder<Widget, IndexView> {}
    private static ThisViewUiBinder uiBinder = GWT.create(ThisViewUiBinder.class);

    @UiField
    Header header;

    public IndexView() {
        initWidget(uiBinder.createAndBindUi(this));
        header.setLoggedOut();
    }

    public void setPresenter(Presenter presenter) {
        header.setPresenter(presenter);
    }
}
