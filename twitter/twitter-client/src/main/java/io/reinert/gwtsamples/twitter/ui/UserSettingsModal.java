package io.reinert.gwtsamples.twitter.ui;

import io.reinert.gwtsamples.twitter.event.UserChangeEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.event.shared.EventBus;

public class UserSettingsModal extends Composite {
    interface UserSettingsModalUiBinder extends UiBinder<HTMLPanel, UserSettingsModal> {}

    private static UserSettingsModalUiBinder ourUiBinder = GWT.create(UserSettingsModalUiBinder.class);

    @UiField TextBox userNameTextBox;

    private final EventBus eventBus;
    private final String userName;

    public UserSettingsModal(EventBus eventBus, String userName) {
        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
        userNameTextBox.setValue(userName);
        initWidget(rootElement);
        this.eventBus = eventBus;
        this.userName = userName;
    }

    @UiHandler("saveButton")
    public void onSaveButtonClick(ClickEvent event) {
        String userName = userNameTextBox.getValue();
        if (!userName.isEmpty() && !userName.equals(this.userName)) {
            // Username is valid and different from former, then notify everyone about the change
            eventBus.fireEventFromSource(new UserChangeEvent(userName), this);
        }
        // Use JSNI to access jquery and close this modal
        hide(getElement().getId());
    }

    private native void hide(String id) /*-{
        $wnd.$('#'+id).modal('hide');
    }-*/;
}