package io.reinert.gwtsamples.twitter;

import io.reinert.gwtsamples.twitter.ui.About;
import io.reinert.gwtsamples.twitter.ui.Home;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public interface SampleClientFactory {
    String getUserName();
    EventBus getEventBus();
    PlaceController getPlaceController();
    Home getHome();
    About getAbout();
}
