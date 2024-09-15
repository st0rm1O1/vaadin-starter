package com.github.st0rm1O1.frontend.page.fallback;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;

@PageTitle("Lost..??")
@Route("404")
@AnonymousAllowed
@PermitAll
public class NotFoundView extends VerticalLayout implements HasErrorParameter<NotFoundException> {

    public NotFoundView() {
        setSpacing(true);

        // Add message and link to home page
        H1 heading = new H1("You seem lost...");
        Anchor homeLink = new Anchor("/", "Go to Home");

        add(heading, homeLink);
    }

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
        return HttpServletResponse.SC_NOT_FOUND;
    }
}

