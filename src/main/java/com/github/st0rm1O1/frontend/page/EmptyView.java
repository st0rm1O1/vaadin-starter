package com.github.st0rm1O1.frontend.page;
import com.github.st0rm1O1.frontend.component.VanillaTilt;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import de.mekaso.vaadin.addon.compani.animation.Animation;
import de.mekaso.vaadin.addon.compani.animation.AnimationBuilder;
import de.mekaso.vaadin.addon.compani.animation.AnimationTypes;
import de.mekaso.vaadin.addon.compani.effect.AttentionSeeker;
import de.mekaso.vaadin.addon.compani.effect.Delay;
import de.mekaso.vaadin.addon.compani.effect.Repeat;
import de.mekaso.vaadin.addon.compani.effect.Speed;

@PageTitle("Empty")
@Menu(icon = "line-awesome/svg/file.svg", order = 0)
@Route(value = "empty")
@RouteAlias(value = "blank")
@RouteAlias(value = "idk")
@AnonymousAllowed
@StyleSheet(Animation.STYLES)
public class EmptyView extends VerticalLayout {

    public EmptyView() {
        setSpacing(false);


//        Image img = new Image("https://start.vaadin.com/images/empty-plant.png", "placeholder plant");
        Image img = new Image("public/empty-plant.png", "placeholder plant");
//        Image img = new Image("public/spidey_venom.jpg", "placeholder plant");
        img.setWidth("250px");
        add(
                new VanillaTilt(img)
                        .reverse(false)                   // reverse the tilt direction
                        .max(10)                          // max tilt rotation (degrees)
                        .startX(0)                        // the starting tilt on the X axis, in degrees.
                        .startY(0)                        // the starting tilt on the Y axis, in degrees.
                        .perspective(1000)                // Transform perspective, the lower the more extreme the tilt gets.
                        .scale(1.2)                         // 2 = 200%, 1.5 = 150%, etc..
                        .speed(500)                       // Speed of the enter/exit transition
                        .transition(true)                // Set a transition on enter/exit.
                        .axis(null)                       // What axis should be enabled. Can be "x" or "y".
                        .reset(true)                     // If the tilt effect has to be reset on exit.
                        .resetToStart(true)              // Whether the exit reset will go to [0,0] (default) or [startX, startY]
//                        .easing("cubic-bezier(.03,.98,.52,.99)") // Easing on enter/exit.
                        .easingSmooth()
                        .glare(true)                    // if it should have a "glare" effect
                        .maxGlare(0.3)                     // the maximum "glare" opacity (1 = 100%, 0.5 = 50%)
                        .glarePrerender(false)            // false = VanillaTilt creates the glare elements for you, otherwise
                                                            // you need to add .js-tilt-glare>.js-tilt-glare-inner by yourself
                        .mouseEventElement(null)         // css-selector or link to an HTML-element that will be listening to mouse events
                        .fullPageListening(false)       // If true, parallax effect will listen to mouse move events on the whole document, not only the selected element
                        .gyroscope(false)                // Boolean to enable/disable device orientation detection,
                        .gyroscopeMinAngleX(-45)         // This is the bottom limit of the device angle on X axis, meaning that a device rotated at this angle would tilt the element as if the mouse was on the left border of the element;
                        .gyroscopeMaxAngleX(45)          // This is the top limit of the device angle on X axis, meaning that a device rotated at this angle would tilt the element as if the mouse was on the right border of the element;
                        .gyroscopeMinAngleY(-45)         // This is the bottom limit of the device angle on Y axis, meaning that a device rotated at this angle would tilt the element as if the mouse was on the top border of the element;
                        .gyroscopeMaxAngleY(45)          // This is the top limit of the device angle on Y axis, meaning that a device rotated at this angle would tilt the element as if the mouse was on the bottom border of the element;
        );
//        img.addClassNames(LumoUtility.BoxShadow.LARGE);


        H2 header = new H2("This place intentionally left empty");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

//        Animator animator = Animator.init(UI.getCurrent());
        AnimationBuilder
                .createBuilderFor(header)
                .create(AnimationTypes.AttentionSeekerAnimation.class)
                .withEffect(AttentionSeeker.growOnHover)
                .withSpeed(Speed.normal)
                .withDelay(Delay.noDelay)
                .withRepeat(Repeat.Once)
                .start();

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
