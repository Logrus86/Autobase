package com.epam.bp.autobase.gwt.client.ui.template;

import com.epam.bp.autobase.gwt.client.activity.Presenter;
import com.epam.bp.autobase.gwt.client.rpc.FetchService;
import com.epam.bp.autobase.model.dto.ColorDto;
import com.epam.bp.autobase.model.dto.ManufacturerDto;
import com.epam.bp.autobase.model.dto.ModelDto;
import com.epam.bp.autobase.model.dto.VehicleDto;
import com.epam.bp.autobase.model.entity.Vehicle;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.constants.ListGroupItemType;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;

import java.math.BigDecimal;
import java.util.List;

public class SearchForm extends Composite implements IsWidget {
    private static ThisViewUiBinder uiBinder = GWT.create(ThisViewUiBinder.class);
    @UiField
    HelpBlock helpBlock_searchError;
    @UiField
    TabListItem tab_bus;
    @UiField
    TabListItem tab_car;
    @UiField
    TabListItem tab_truck;
    @UiField
    Select select_model;
    @UiField
    Select select_vendor;
    @UiField
    Select select_color;
    @UiField
    Select select_fuelType;
    @UiField
    Input input_notOlder;
    @UiField
    Input input_mileage;
    @UiField
    Input input_rent;
    @UiField
    Input input_busPassSeats;
    @UiField
    Input input_busStandPlaces;
    @UiField
    Select select_busDoorsNumber;
    @UiField
    Select select_carPassSeats;
    @UiField
    Select select_carDoorsNumber;
    @UiField
    CheckBox checkbox_carConditioner;
    @UiField
    Input input_truckPayload;
    @UiField
    CheckBox checkbox_truckEnclosed;
    @UiField
    CheckBox checkbox_truckTipper;
    @UiField
    Button button_search;
    @UiField
    Heading heading_panelHeader;
    @UiField
    PanelBody panelBody_searchForm;
    @UiField
    PanelBody panelBody_searchResult;
    @UiField
    ListGroup listGroup_searchResult;
    @UiField
    Panel panel_searchForm;
    @UiField
    PanelFooter panelFooter;

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
                    select_model.add(option);
                }
                select_model.refresh();
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
                    select_vendor.add(option);
                }
                select_vendor.refresh();
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
                    select_color.add(option);
                }
                select_color.refresh();
            }
        });
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @UiHandler("button_search")
    public void onButtonSearchClick(ClickEvent e) {
        VehicleDto dto = new VehicleDto();
        if (!"".equals(select_model.getValue())) dto.setModelDto(new ModelDto(select_model.getValue()));
        if (!"".equals(select_vendor.getValue())) dto.setManufacturerDto(new ManufacturerDto(select_vendor.getValue()));
        if (!"".equals(select_color.getValue())) dto.setColorDto(new ColorDto(select_color.getValue(), true));
        if (!"".equals(select_fuelType.getValue()))
            dto.setFuelType(Vehicle.Fuel.valueOf(select_fuelType.getValue().toUpperCase()));
        if (input_notOlder.getValue() != null) dto.setProductionYear(Integer.valueOf(input_notOlder.getValue()));
        if (input_mileage.getValue() != null) dto.setMileage(new BigDecimal(input_mileage.getValue()));
        if (input_rent.getValue() != null) dto.setRentPrice(new BigDecimal(input_rent.getValue()));
        if (tab_car.isActive()) {
            dto.setType(Vehicle.Type.CAR)
                    .setWithConditioner(checkbox_carConditioner.getValue())
                    .setPassengerSeatsNumber(Integer.parseInt(select_carPassSeats.getValue()))
                    .setDoorsNumber(Integer.parseInt(select_carDoorsNumber.getValue()));
        } else if (tab_bus.isActive()) {
            dto.setType(Vehicle.Type.BUS)
                    .setDoorsNumber(Integer.parseInt(select_busDoorsNumber.getValue()));
            if (input_busPassSeats.getValue() != null)
                dto.setPassengerSeatsNumber(Integer.parseInt(input_busPassSeats.getValue()));
            if (input_busStandPlaces.getValue() != null)
                dto.setStandingPlacesNumber(Integer.parseInt(input_busStandPlaces.getValue()));
        } else {
            dto.setType(Vehicle.Type.TRUCK)
                    .setEnclosed(checkbox_truckEnclosed.getValue())
                    .setTipper(checkbox_truckTipper.getValue());
            if (input_truckPayload.getValue() != null) dto.setMaxPayload(new BigDecimal(input_truckPayload.getValue()));
        }
        FetchService.App.getInstance().findVehicles(dto, new AsyncCallback<List<VehicleDto>>() {
            @Override
            public void onFailure(Throwable caught) {
                helpBlock_searchError.setText("Search error on server: " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<VehicleDto> result) {
                if ((result == null) || (result.isEmpty()))
                    helpBlock_searchError.setText("Nothing was found. Try again.");
                else {
                    // proper way: presenter.goTo(new SearchResult("search_params"));
                    // it required separate view (place), taking search parameters and working like permalink
                    // short way:
                    heading_panelHeader.setText("Find "+result.size()+" vehicle(s):");
                    panelBody_searchForm.setVisible(false);
                    panelBody_searchResult.setVisible(true);
                    boolean isColored = false;
                    for (VehicleDto vehicleDto : result) {
                        ListGroupItem item = new ListGroupItem();
                        item.setText(vehicleDto.buildLazyEntity().toString());
                        if (!isColored) {
                            item.setType(ListGroupItemType.INFO);
                            isColored = true;
                        } else isColored = false;
                        listGroup_searchResult.add(item);
                    }
                    panelFooter.setVisible(false);
                    panel_searchForm.setWidth("85%");
                }
            }
        });
    }

    interface ThisViewUiBinder extends UiBinder<Widget, SearchForm> {
    }
}
