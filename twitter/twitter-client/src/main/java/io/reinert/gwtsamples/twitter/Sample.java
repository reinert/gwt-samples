package io.reinert.gwtsamples.twitter;

import io.reinert.gwtsamples.twitter.place.HomePlace;
import io.reinert.gwtsamples.twitter.ui.TwitterShell;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sample implements EntryPoint {

    private final Place defaultPlace = new HomePlace();
    private TwitterShell appContainer;

    @Override
    public void onModuleLoad() {
        // Create ClientFactory using deferred binding so we can replace with different
        // impls in gwt.xml
        SampleClientFactory clientFactory = GWT.create(SampleClientFactory.class);
        EventBus eventBus = clientFactory.getEventBus();
        PlaceController placeController = clientFactory.getPlaceController();
        appContainer = new TwitterShell(eventBus, clientFactory.getUserName());

        // Start ActivityManager for the main widget with our ActivityMapper
        ActivityMapper activityMapper = new SampleActivittyMapper(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(appContainer);

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        PlaceHistoryMapper historyMapper = GWT.create(SamplePlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        RootPanel.get().add(appContainer);

        // Goes to place represented on URL or default place
        historyHandler.handleCurrentHistory();
    }
}
