package io.reinert.gwtsamples.twitter.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class UserChangeEvent extends GwtEvent<UserChangeEvent.Handler> {

    public interface Handler extends EventHandler {
        void onUserChange(UserChangeEvent event);
    }

    public static Type<Handler> TYPE = new Type<Handler>();

    private final String newUserName;

    public UserChangeEvent(String newUserName) {
        this.newUserName = newUserName;
    }

    public Type<Handler> getAssociatedType() {
        return TYPE;
    }

    public String getNewUserName() {
        return newUserName;
    }

    protected void dispatch(Handler handler) {
        handler.onUserChange(this);
    }
}
