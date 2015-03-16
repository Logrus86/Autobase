package com.epam.bp.autobase.gwt.client.rpc;

import com.epam.bp.autobase.model.dto.ColorDto;
import com.epam.bp.autobase.model.dto.ManufacturerDto;
import com.epam.bp.autobase.model.dto.ModelDto;
import com.epam.bp.autobase.model.dto.VehicleDto;
import com.epam.bp.autobase.model.entity.Vehicle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("FetchService")
public interface FetchService extends RemoteService {

    List<ModelDto> fetchModels();

    List<ManufacturerDto> fetchManufacturers();

    List<ColorDto> fetchColors();

    Vehicle findVehicle(VehicleDto dto);

    static class App {
        private static FetchServiceAsync ourInstance = GWT.create(FetchService.class);

        public static synchronized FetchServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
