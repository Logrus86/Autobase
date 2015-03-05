package com.epam.bp.autobase.gwt.client.rpc;

import com.epam.bp.autobase.model.entity.Model;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.gwtbootstrap3.extras.select.client.ui.Select;

import java.util.List;

public class FetchListToSelectCallback implements AsyncCallback<List<Model>> {
    Select select;

    public FetchListToSelectCallback(Select select) {
        this.select = select;
    }

    @Override
    public void onFailure(Throwable caught) {
        select.setHeader("Fetch list from server error");
    }

    @Override
    public void onSuccess(List<Model> result) {
        if (result != null) {
            String[] values = new String[result.size()];
            for (int i = 0; i < result.size(); i++) values[i] = result.get(i).getValue();
            select.setValues(values);
            select.refresh();
        }
    }
}
