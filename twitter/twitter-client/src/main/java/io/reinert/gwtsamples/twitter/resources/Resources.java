package io.reinert.gwtsamples.twitter.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface Resources extends ClientBundle {

    @Source("Style.css")
    SampleStyle style();

//    @Source("Logo.jpg")
//    ImageResource logo();

    public static final class Util {

        private static Resources resources;

        public static void ensureStyle() {
            if (resources == null)
                resources = GWT.create(Resources.class);
            resources.style().ensureInjected();
        }
    }
}
