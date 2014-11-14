package io.reinert.gwtsamples.twitter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

import io.reinert.gwtsamples.twitter.event.UserChangeEvent;
import io.reinert.gwtsamples.twitter.ui.About;
import io.reinert.gwtsamples.twitter.ui.Home;
import io.reinert.requestor.Requestor;

public class SampleClientFactoryImpl implements SampleClientFactory {

    private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);
    private final Requestor requestor = GWT.create(Requestor.class);
    private String userName = "anonymous";
    private Home home;
    private About about;

    public SampleClientFactoryImpl() {
        eventBus.addHandler(UserChangeEvent.TYPE, new UserChangeEvent.Handler() {
            @Override
            public void onUserChange(UserChangeEvent event) {
                userName = event.getNewUserName();
            }
        });
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public PlaceController getPlaceController() {
        return placeController;
    }

    @Override
    public Requestor getRequestor() {
        return requestor;
    }

    @Override
    public Home getHome() {
        if (home == null)
            home = new Home();
        return home;
    }

    @Override
    public About getAbout() {
        if (about == null)
            about = new About();
        return about;
    }
}
