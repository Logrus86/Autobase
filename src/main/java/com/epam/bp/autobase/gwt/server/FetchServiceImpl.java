package com.epam.bp.autobase.gwt.server;

import com.epam.bp.autobase.gwt.client.rpc.FetchService;
import com.epam.bp.autobase.model.entity.Model;
import com.epam.bp.autobase.util.ListProducer;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.util.List;

public class FetchServiceImpl extends RemoteServiceServlet implements FetchService {
    @Inject
    private Logger logger;
    @Inject
    ListProducer listProducer;

    @Override
    public List<Model> fetchModels() {
        logger.info("fetching models...");
        return listProducer.getModels();
    }
}
