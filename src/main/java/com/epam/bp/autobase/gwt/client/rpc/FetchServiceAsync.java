package com.epam.bp.autobase.gwt.client.rpc;

import com.epam.bp.autobase.model.dto.ModelDto;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface FetchServiceAsync {
    void fetchModels(AsyncCallback<List<ModelDto>> async);
}
