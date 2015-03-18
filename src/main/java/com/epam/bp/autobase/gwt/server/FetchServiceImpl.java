package com.epam.bp.autobase.gwt.server;

import com.epam.bp.autobase.gwt.client.rpc.FetchService;
import com.epam.bp.autobase.model.dto.ColorDto;
import com.epam.bp.autobase.model.dto.ManufacturerDto;
import com.epam.bp.autobase.model.dto.ModelDto;
import com.epam.bp.autobase.model.dto.VehicleDto;
import com.epam.bp.autobase.service.*;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.util.List;

public class FetchServiceImpl extends RemoteServiceServlet implements FetchService {
    @Inject
    private Logger logger;
    @Inject
    ModelService ms;
    @Inject
    ManufacturerService mfs;
    @Inject
    ColorService cs;
    @Inject
    VehicleService vs;

    @Override
    public List<ModelDto> fetchModels() {
        logger.debug("fetching models...");
        try {
            return ms.getAll();
        } catch (ServiceException e) {
            logger.error("Models fetching failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ManufacturerDto> fetchManufacturers() {
        logger.debug("fetching vendors...");
        try {
            return mfs.getAll();
        } catch (ServiceException e) {
            logger.error("Vendors fetching failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ColorDto> fetchColors() {
        logger.debug("fetching colors...");
        try {
            return cs.getAll();
        } catch (ServiceException e) {
            logger.error("Colors fetching failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<VehicleDto> findVehicles(VehicleDto dto) {
        try {
            logger.info("Searching for vehicle: "+dto.buildLazyEntity().toString());
            List<VehicleDto> result = vs.findByParams(dto);
            logger.info("Found: "+result.size()+" vehicle(s)");
            return result;
        } catch (ServiceException e) {
            logger.error("Vehicle searching failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
