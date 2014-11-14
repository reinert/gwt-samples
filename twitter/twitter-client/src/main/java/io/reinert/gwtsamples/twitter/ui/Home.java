package io.reinert.gwtsamples.twitter.ui;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.AbstractHasData;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;

import io.reinert.gwtsamples.twitter.model.Tweet;
import io.reinert.gwtsamples.twitter.resources.Resources;

public class Home extends Composite {

    public interface Handler {
        void onSendTweet(String message);
        void onLoadButtonClicked();
        void onFaultyLoadButtonClicked();
    }

    interface HomeUiBinder extends UiBinder<HTMLPanel, Home> {}

    private static HomeUiBinder ourUiBinder = GWT.create(HomeUiBinder.class);

    @UiField Resources res;
    @UiField TextArea compose;
    @UiField SimplePanel tweetsContainer;

    private final AbstractHasData<Tweet> tweetsCelList;
    private Handler handler;

    public Home() {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

        Resources.Util.ensureStyle(); // Ensure SampleStyle injected

        compose.getElement().setAttribute("placeholder", "Write your tweet and press Ctrl + Enter...");
        tweetsCelList = new CellList<>(new TweetCell());
        tweetsContainer.add(tweetsCelList);

        initWidget(rootElement);
    }

    @UiHandler("compose")
    public void onComposeKeyUp(KeyUpEvent e) {
        if (isControlEnterPressed(e)) {
            if (handler != null) handler.onSendTweet(compose.getValue()); // Delegate logic execution to presenter
            compose.setValue(null); // Clean compose text
        }
    }

    @UiHandler("loadButton")
    public void onLoadButtonClick(ClickEvent e) {
        if (handler != null) handler.onLoadButtonClicked();
    }

    @UiHandler("faultyLoadButton")
    public void onFaultyLoadButtonClick(ClickEvent e) {
        if (handler != null) handler.onFaultyLoadButtonClicked();
    }

    private boolean isControlEnterPressed(KeyUpEvent event) {
        return event.isControlKeyDown() && (event.getNativeKeyCode() == 10 || event.getNativeKeyCode() == 13);
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setTweets(List<Tweet> result) {
        tweetsCelList.setRowCount(result.size(), true);
        tweetsCelList.setRowData(result);
    }
}