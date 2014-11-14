package io.reinert.gwtsamples.twitter.activity;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import io.reinert.gdeferred.DoneCallback;
import io.reinert.gdeferred.FailCallback;
import io.reinert.gwtsamples.twitter.event.UserChangeEvent;
import io.reinert.gwtsamples.twitter.model.Tweet;
import io.reinert.gwtsamples.twitter.ui.Home;
import io.reinert.requestor.ListDoneCallback;
import io.reinert.requestor.Requestor;

public class HomeActivity extends AbstractActivity implements Home.Handler {

    private final Requestor requestor;
    private final Home home;
    private String userName;

    public HomeActivity(Home home, Requestor requestor, String userName) {
        this.home = home;
        this.userName = userName;
        this.requestor = requestor;
    }

    @Override
    public void onSendTweet(String message) {
        final Tweet tweet = new Tweet(userName, message);
        requestor.request("/server/tweets").payload(tweet).post().done(new DoneCallback<Void>() {
            @Override
            public void onDone(Void result) {
                loadTweets();
            }
        }).fail(new FailCallback<Throwable>() {
            @Override
            public void onFail(Throwable result) {
                Window.alert("Error while sending tweet.");
            }
        });
    }

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        requestor.request("/server/tweets").get(Tweet.class, List.class).done(new ListDoneCallback<Tweet>() {
            @Override
            public void onDone(List<Tweet> result) {
                // Listen to userName changes
                eventBus.addHandler(UserChangeEvent.TYPE, new UserChangeEvent.Handler() {
                    @Override
                    public void onUserChange(UserChangeEvent event) {
                        userName = event.getNewUserName();
                    }
                });

                home.setHandler(HomeActivity.this); // Bind listener
                home.setTweets(result);
                panel.setWidget(home); // Only display screen AFTER loading tweets
            }
        }).fail(new FailCallback<Throwable>() {
            @Override
            public void onFail(Throwable result) {
                Window.alert("Error while retrieving tweets.");
            }
        });
    }

    private void loadTweets() {
        requestor.request("/server/tweets").get(Tweet.class, List.class).done(new ListDoneCallback<Tweet>() {
            @Override
            public void onDone(List<Tweet> result) {
                home.setTweets(result);
            }
        }).fail(new FailCallback<Throwable>() {
            @Override
            public void onFail(Throwable result) {
                Window.alert("Error while retrieving tweets.");
            }
        });
    }

    @Override
    public void onStop() {
        home.setHandler(null); // Unbind listener
    }
}
