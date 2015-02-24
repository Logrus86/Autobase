package com.epam.bp.autobase.gwt.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class Client extends Place {
    private String placeName;

    public Client(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public static class Tokenizer implements PlaceTokenizer<Client> {

        @Override
        public Client getPlace(String token) {
            return new Client(token);
        }

        @Override
        public String getToken(Client place) {
            return place.getPlaceName();
        }
    }
}
