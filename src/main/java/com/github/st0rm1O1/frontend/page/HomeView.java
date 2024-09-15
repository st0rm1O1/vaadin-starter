package com.github.st0rm1O1.frontend.page;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("")
@AnonymousAllowed
public class HomeView extends VerticalLayout {
    public HomeView() {
        add(
                new H1("Hello! Home-View Here."),
                new Anchor("admin/test", "Go to Admin Page")
        );
    }
}
