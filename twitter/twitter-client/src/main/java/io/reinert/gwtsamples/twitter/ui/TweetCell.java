package io.reinert.gwtsamples.twitter.ui;

import io.reinert.gwtsamples.twitter.model.Tweet;
import io.reinert.gwtsamples.twitter.resources.Resources;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiRenderer;

import java.util.Set;

public class TweetCell extends AbstractCell<Tweet> {

    interface MyUiRenderer extends UiRenderer {
        void render(SafeHtmlBuilder sb, Tweet tweet);
    }

    private static MyUiRenderer uiRenderer = GWT.create(MyUiRenderer.class);

    public TweetCell(String... consumedEvents) {
        super(consumedEvents);
        Resources.Util.ensureStyle(); // Ensure style injected
    }

    public TweetCell(Set<String> consumedEvents) {
        super(consumedEvents);
        Resources.Util.ensureStyle(); // Ensure style injected
    }

    @Override
    public void render(Context context, Tweet value, SafeHtmlBuilder sb) {
        uiRenderer.render(sb, value);
    }
}