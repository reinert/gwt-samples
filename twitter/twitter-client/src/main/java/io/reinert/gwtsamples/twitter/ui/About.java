package io.reinert.gwtsamples.twitter.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HTMLPanel;

public class About extends Composite {

    public interface Handler {
        void onHomeClick();
        void upload(JavaScriptObject blob, String filename);
    }

    interface AboutUiBinder extends UiBinder<HTMLPanel, About> {}

    private static AboutUiBinder ourUiBinder = GWT.create(AboutUiBinder.class);

    @UiField FileUpload upload;
    private Handler handler;
    private HTMLPanel rootElement;

    public About() {
        rootElement = ourUiBinder.createAndBindUi(this);
        initWidget(rootElement);
    }

    @UiHandler("homeButton")
    public void onHomeButtonClick(ClickEvent event) {
        if (handler != null) handler.onHomeClick();

        // upload
        // final String filename = upload.getFilename();
        // handler.upload(getFile(upload.getElement()), filename.substring(filename.lastIndexOf('\\') + 1));
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void createImage(JavaScriptObject blob) {
        appendImage(blob, rootElement.getElement());
    }

    private native void appendImage(JavaScriptObject blob, Element container) /*-{
        var img = $doc.createElement('img');
        img.onload = function() {
          $wnd.URL.revokeObjectURL(img.src); // Clean up after yourself.
        };
        img.src = $wnd.URL.createObjectURL(blob);
        container.appendChild(img);
    }-*/;

    private native JavaScriptObject getFile(Element container) /*-{
        return container.files[0];
    }-*/;
}