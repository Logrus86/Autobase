package com.epam.bp.autobase.gwt.client.ui.template;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class Footer extends Composite implements IsWidget {
    interface ThisViewUiBinder extends UiBinder<Widget, Footer> {}
    private static ThisViewUiBinder uiBinder = GWT.create(ThisViewUiBinder.class);

    public Footer() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
