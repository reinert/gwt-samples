package io.reinert.gwtsamples.twitter.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class About extends Composite {

    public interface Handler {
        void onHomeClick();
    }

    interface AboutUiBinder extends UiBinder<HTMLPanel, About> {}

    private static AboutUiBinder ourUiBinder = GWT.create(AboutUiBinder.class);

    private Handler handler;

    public About() {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
        initWidget(rootElement);
    }

    @UiHandler("homeButton")
    public void onHomeButtonClick(ClickEvent event) {
        if (handler != null) handler.onHomeClick();
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}