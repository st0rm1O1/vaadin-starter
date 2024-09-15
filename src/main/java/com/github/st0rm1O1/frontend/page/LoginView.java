package com.github.st0rm1O1.frontend.page;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends Composite<LoginOverlay> implements BeforeEnterObserver {
    public LoginView() {
        getContent().setOpened(true);
        getContent().setAction("login");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            getContent().setError(true);
        }
    }
}
