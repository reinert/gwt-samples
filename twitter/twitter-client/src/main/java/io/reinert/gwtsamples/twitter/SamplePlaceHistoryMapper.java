package io.reinert.gwtsamples.twitter;

import io.reinert.gwtsamples.twitter.place.AboutPlace;
import io.reinert.gwtsamples.twitter.place.HomePlace;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;

public class SamplePlaceHistoryMapper implements PlaceHistoryMapper {

    @Override
    public Place getPlace(String token) {
        switch (token) {
            case "home": return new HomePlace();
            case "about": return new AboutPlace();
            default: return null;
        }
    }

    @Override
    public String getToken(Place place) {
        if (place instanceof HomePlace)
            return "home";
        else if (place instanceof AboutPlace)
            return "about";

        return null;
    }
}
