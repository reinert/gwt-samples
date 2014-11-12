package io.reinert.gwtsamples.twitter.ui;

import io.reinert.gwtsamples.twitter.event.UserChangeEvent;
import io.reinert.gwtsamples.twitter.place.AboutPlace;
import io.reinert.gwtsamples.twitter.place.HomePlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

public class TwitterShell extends Composite implements AcceptsOneWidget {
    interface TwitterShellUiBinder extends UiBinder<HTMLPanel, TwitterShell> {}

    private static TwitterShellUiBinder ourUiBinder = GWT.create(TwitterShellUiBinder.class);

    @UiField Element home;
    @UiField Element about;
    @UiField Element homeLi;
    @UiField Element aboutLi;
    @UiField Element userName;
    @UiField SimplePanel center;
    @UiField(provided = true) UserSettingsModal modal;

    public TwitterShell(EventBus eventBus, String userName) {
        // Provided uiFields must be instantiated BEFORE createAndBindUi
        modal = new UserSettingsModal(eventBus, userName);
        modal.getElement().setId("myModal");

        HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

        this.userName.setInnerText(userName);

        initWidget(rootElement);

        eventBus.addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {
            @Override
            public void onPlaceChange(PlaceChangeEvent event) {
                final Place place = event.getNewPlace();
                if (place instanceof AboutPlace) {
                    homeLi.removeClassName("active");
                    aboutLi.addClassName("active");
                } else if (place instanceof HomePlace) {
                    homeLi.addClassName("active");
                    aboutLi.removeClassName("active");
                }
            }
        });

        eventBus.addHandler(UserChangeEvent.TYPE, new UserChangeEvent.Handler() {
            @Override
            public void onUserChange(UserChangeEvent event) {
                TwitterShell.this.userName.setInnerText(event.getNewUserName());
            }
        });
    }

    public void setMainWidget(IsWidget widget) {
        center.setWidget(widget);
    }

    @Override
    public void setWidget(IsWidget w) {
        setMainWidget(w);
    }
}