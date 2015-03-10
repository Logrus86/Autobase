package com.epam.bp.autobase.gwt.client.ui.template;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.rpc.FetchService;
import com.epam.bp.autobase.model.dto.ColorDto;
import com.epam.bp.autobase.model.dto.ManufacturerDto;
import com.epam.bp.autobase.model.dto.ModelDto;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;

import java.util.List;

public class SearchForm extends Composite implements IsWidget {
    private static ThisViewUiBinder uiBinder = GWT.create(ThisViewUiBinder.class);
    @UiField
    Select modelSelect;
    @UiField
    Select vendorSelect;
    @UiField
    Select colorSelect;
    private Presenter presenter;

    public SearchForm() {
        initWidget(uiBinder.createAndBindUi(this));
        FetchService.App.getInstance().fetchModels(new AsyncCallback<List<ModelDto>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<ModelDto> result) {
                for (ModelDto modelDto : result) {
                    Option option = new Option();
                    option.setText(modelDto.getValue());
                    option.setValue(modelDto.getValue());
                    modelSelect.add(option);
                }
                modelSelect.refresh();
            }
        });
        FetchService.App.getInstance().fetchManufacturers(new AsyncCallback<List<ManufacturerDto>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<ManufacturerDto> result) {
                for (ManufacturerDto manufacturerDto : result) {
                    Option option = new Option();
                    option.setText(manufacturerDto.getValue());
                    option.setValue(manufacturerDto.getValue());
                    vendorSelect.add(option);
                }
                vendorSelect.refresh();
            }
        });
        FetchService.App.getInstance().fetchColors(new AsyncCallback<List<ColorDto>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<ColorDto> result) {
                for (ColorDto colorDto : result) {
                    Option option = new Option();
                    option.setText(colorDto.getValue_en());
                    option.setValue(colorDto.getValue_en());
                    colorSelect.add(option);
                }
                colorSelect.refresh();
            }
        });
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    interface ThisViewUiBinder extends UiBinder<Widget, SearchForm> {
    }
}
