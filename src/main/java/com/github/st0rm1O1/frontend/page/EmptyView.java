package com.github.st0rm1O1.frontend.page;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Empty")
@Menu(icon = "line-awesome/svg/file.svg", order = 0)
@Route(value = "empty")
@RouteAlias(value = "blank")
@RouteAlias(value = "idk")
@AnonymousAllowed
public class EmptyView extends VerticalLayout {

    public EmptyView() {
        setSpacing(false);

//        Image img = new Image("https://start.vaadin.com/images/empty-plant.png", "placeholder plant");
        Image img = new Image("public/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("This place intentionally left empty");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
