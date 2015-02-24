package com.epam.bp.autobase.gwt.client.mvp;

import com.epam.bp.autobase.gwt.client.place.Client;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({
   //     Index.Tokenizer.class,
        Client.Tokenizer.class
})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper{
}
