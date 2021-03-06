package io.reinert.gwtsamples.twitter;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

import io.reinert.gwtsamples.twitter.ui.About;
import io.reinert.gwtsamples.twitter.ui.Home;
import io.reinert.requestor.Requestor;

public interface SampleClientFactory {
    String getUserName();
    EventBus getEventBus();
    PlaceController getPlaceController();
    Requestor getRequestor();
    Home getHome();
    About getAbout();
}
