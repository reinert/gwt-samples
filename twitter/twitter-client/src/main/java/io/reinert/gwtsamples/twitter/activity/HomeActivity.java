package io.reinert.gwtsamples.twitter.activity;

import io.reinert.gwtsamples.twitter.event.UserChangeEvent;
import io.reinert.gwtsamples.twitter.model.Tweet;
import io.reinert.gwtsamples.twitter.service.TweetServiceAsync;
import io.reinert.gwtsamples.twitter.ui.Home;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import java.util.List;

public class HomeActivity extends AbstractActivity implements Home.Handler {

    private final Home home;
    private String userName;

    public HomeActivity(Home home, String userName) {
        this.home = home;
        this.userName = userName;
    }

    @Override
    public void onSendTweet(String message) {
        final Tweet tweet = new Tweet(userName, message);
        TweetServiceAsync.Util.getInstance().tweet(tweet, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Erro ao enviar o tweet.");
            }

            @Override
            public void onSuccess(String result) {
                loadTweets();
            }
        });
    }

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        TweetServiceAsync.Util.getInstance().getAll(new AsyncCallback<List<Tweet>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Erro ao carregar os tweets.");
            }

            @Override
            public void onSuccess(List<Tweet> result) {
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
        });
    }

    private void loadTweets() {
        TweetServiceAsync.Util.getInstance().getAll(new AsyncCallback<List<Tweet>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Erro ao carregar os tweets.");
            }

            @Override
            public void onSuccess(List<Tweet> result) {
                home.setTweets(result);
            }
        });
    }

    @Override
    public void onStop() {
        home.setHandler(null); // Unbind listener
    }
}
