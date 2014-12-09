package io.reinert.gwtsamples.twitter.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import io.reinert.gdeferred.DoneCallback;
import io.reinert.gdeferred.FailCallback;
import io.reinert.gdeferred.ProgressCallback;
import io.reinert.gwtsamples.twitter.ui.About;
import io.reinert.requestor.Payload;
import io.reinert.requestor.RequestProgress;
import io.reinert.requestor.Requestor;
import io.reinert.requestor.ResponseType;

public class AboutActivity extends AbstractActivity implements About.Handler {

    private final About about;
    private final PlaceController placeController;
    private final Requestor requestor;

    public AboutActivity(About about, PlaceController placeController, Requestor requestor) {
        this.about = about;
        this.placeController = placeController;
        this.requestor = requestor;
    }

    @Override
    public void onHomeClick() {
        //placeController.goTo(new HomePlace());
        requestor.request("/server/files")
                .accept("image/jpg")
                .responseType(ResponseType.BLOB)
                .header("Cache-Control", "no-cache")
                .get(Payload.class)
                .done(new DoneCallback<Payload>() {
                    @Override
                    public void onDone(Payload result) {
                        Element e = Document.get().getElementById("imageProgress");
                        e.getStyle().setWidth(100, Style.Unit.PCT);
                        e.getFirstChildElement().setInnerText("Completed");

                        about.createImage(result.isJavaScriptObject());
                    }
                }).fail(new FailCallback<Throwable>() {
                    @Override
                    public void onFail(Throwable result) {
                        Window.alert("Ooops! Could not load the image!");
                    }
                }).progress(new ProgressCallback<RequestProgress>() {
                    @Override
                    public void onProgress(RequestProgress progress) {
                        Element e = Document.get().getElementById("imageProgress");
                        GWT.log("Is Length Computable? " + progress.isLengthComputable());
                        GWT.log("Loaded: " + progress.loaded());
                        GWT.log("Total: " + progress.total());
                        final double value = progress.loaded() / progress.total();
                        e.getStyle().setWidth(value, Style.Unit.PCT);
                        e.getFirstChildElement().setInnerText(String.valueOf(value));
                    }
                });
    }

    @Override
    public void upload(JavaScriptObject blob, String filename) {
        requestor.request("/server/files")
                .contentType("*/*")
                .accept("*/*")
                .header("File-Name", filename)
                .payload(new Payload(blob))
                .put(String.class)
                .done(new DoneCallback<String>() {
                    @Override
                    public void onDone(String result) {
                        Window.alert(result);
                    }
                }).fail(new FailCallback<Throwable>() {
                    @Override
                    public void onFail(Throwable result) {
                        Window.alert(result.getMessage());
                    }
                }).progress(new ProgressCallback<RequestProgress>() {
                    @Override
                    public void onProgress(RequestProgress progress) {
                        Element e = Document.get().getElementById("imageProgress");
                        final double value = progress.loaded() / progress.total();
                        e.getStyle().setWidth(value, Style.Unit.PCT);
                        e.getFirstChildElement().setInnerText(String.valueOf(value));
                    }
                });
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        about.setHandler(this); // Bind listener
        panel.setWidget(about);
    }

    @Override
    public void onStop() {
        about.setHandler(null); // Unbind listener
    }
}
