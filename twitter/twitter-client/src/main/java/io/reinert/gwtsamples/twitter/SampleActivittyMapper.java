package io.reinert.gwtsamples.twitter;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import io.reinert.gwtsamples.twitter.activity.AboutActivity;
import io.reinert.gwtsamples.twitter.activity.HomeActivity;
import io.reinert.gwtsamples.twitter.place.AboutPlace;
import io.reinert.gwtsamples.twitter.place.HomePlace;

public class SampleActivittyMapper implements ActivityMapper {

    private final SampleClientFactory clientFactory;

    public SampleActivittyMapper(SampleClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof HomePlace)
            return new HomeActivity(clientFactory.getHome(), clientFactory.getRequestor(), clientFactory.getUserName());
        else if (place instanceof AboutPlace)
            return new AboutActivity(clientFactory.getAbout(), clientFactory.getPlaceController());

        return null;
    }
}
