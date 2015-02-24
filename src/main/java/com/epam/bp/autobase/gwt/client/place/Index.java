package com.epam.bp.autobase.gwt.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class Index extends Place {

   public static class Tokenizer implements PlaceTokenizer<Index> {
        @Override
        public String getToken(Index place) {
            return "";
        }

        @Override
        public Index getPlace(String token) {
            return new Index();
        }
    }
}
