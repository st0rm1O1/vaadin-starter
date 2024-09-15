package com.github.st0rm1O1.frontend.component;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.shared.ui.LoadMode;

/**
 * A Vaadin Flow component that wraps any Vaadin {@link Component} and applies the VanillaTilt.js effect to it.
 *
 * <p>
 * VanillaTilt is a lightweight library that applies a 3D tilt effect to elements.
 * This component allows customization of the tilt effect by chaining configuration methods.
 * The VanillaTilt JavaScript library is loaded with eager loading mode when the component is attached.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     VanillaTilt tiltComponent = new VanillaTilt(new Button("Tilt Me"))
 *                                     .max(15)
 *                                     .scale(1.2)
 *                                     .glare(true);
 * </pre>
 * </p>
 *
 * <p>
 * Available options:
 * <pre>
 * {
 *     reverse:                false,  // reverse the tilt direction
 *     max:                    15,     // max tilt rotation (degrees)
 *     startX:                 0,      // the starting tilt on the X axis, in degrees.
 *     startY:                 0,      // the starting tilt on the Y axis, in degrees.
 *     perspective:            1000,   // Transform perspective, the lower the more extreme the tilt gets.
 *     scale:                  1,      // 2 = 200%, 1.5 = 150%, etc..
 *     speed:                  300,    // Speed of the enter/exit transition
 *     transition:             true,   // Set a transition on enter/exit.
 *     axis:                   null,   // What axis should be enabled. Can be "x" or "y".
 *     reset:                  true,   // If the tilt effect has to be reset on exit.
 *     "reset-to-start":       true,   // Whether the exit reset will go to [0,0] (default) or [startX, startY]
 *     easing:                 "cubic-bezier(.03,.98,.52,.99)",    // Easing on enter/exit.
 *     glare:                  false,  // if it should have a "glare" effect
 *     "max-glare":            1,      // the maximum "glare" opacity (1 = 100%, 0.5 = 50%)
 *     "glare-prerender":      false,  // false = VanillaTilt creates the glare elements for you, otherwise
 *                                     // you need to add .js-tilt-glare>.js-tilt-glare-inner by yourself
 *     "mouse-event-element":  null,   // css-selector or link to an HTML-element that will be listening to mouse events
 *     "full-page-listening":  false,  // If true, parallax effect will listen to mouse move events on the whole document, not only the selected element
 *     gyroscope:              true,   // Boolean to enable/disable device orientation detection,
 *     gyroscopeMinAngleX:     -45,    // This is the bottom limit of the device angle on X axis, meaning that a device rotated at this angle would tilt the element as if the mouse was on the left border of the element;
 *     gyroscopeMaxAngleX:     45,     // This is the top limit of the device angle on X axis, meaning that a device rotated at this angle would tilt the element as if the mouse was on the right border of the element;
 *     gyroscopeMinAngleY:     -45,    // This is the bottom limit of the device angle on Y axis, meaning that a device rotated at this angle would tilt the element as if the mouse was on the top border of the element;
 *     gyroscopeMaxAngleY:     45,     // This is the top limit of the device angle on Y axis, meaning that a device rotated at this angle would tilt the element as if the mouse was on the bottom border of the element;
 *     gyroscopeSamples:       10      // How many gyroscope moves to decide the starting position.
 * }
 * </pre>
 * </p>
 */
@Tag("div")
@JavaScript(value = "context://public/vanilla-tilt.js", loadMode = LoadMode.EAGER)
public class VanillaTilt extends Div {

    // StringBuilder to build the VanillaTilt configuration options.
    private final StringBuilder optionsBuilder = new StringBuilder();

    /**
     * Constructor for VanillaTilt. Wraps any Vaadin {@link Component} to apply the tilt effect.
     *
     * @param component the Vaadin component to apply the VanillaTilt effect to.
     */
    public VanillaTilt(Component component) {
        add(component);
    }


    /**
     * Applies the VanillaTilt effect with the current options when the component is attached to the DOM.
     * The optionsBuilder constructs the configuration and passes it to the VanillaTilt.init() JavaScript function.
     */
    private void applyVanillaTilt() {
        String options = optionsBuilder.isEmpty() ? "{}" : "{" + optionsBuilder + "}";
        getElement().executeJs("VanillaTilt.init(this, " + options + ");");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        applyVanillaTilt();
    }

    /**
     * Sets whether the tilt direction is reversed.
     *
     * @param reverse true if the tilt direction should be reversed, false otherwise.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt reverse(boolean reverse) {
        optionsBuilder.append("\"reverse\": ").append(reverse).append(", ");
        return this;
    }

    /**
     * Sets the maximum tilt rotation in degrees.
     *
     * @param max the maximum tilt rotation (in degrees).
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt max(int max) {
        optionsBuilder.append("\"max\": ").append(max).append(", ");
        return this;
    }

    /**
     * Sets the starting tilt on the X-axis in degrees.
     *
     * @param startX the initial tilt angle on the X-axis.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt startX(int startX) {
        optionsBuilder.append("\"startX\": ").append(startX).append(", ");
        return this;
    }

    /**
     * Sets the starting tilt on the Y-axis in degrees.
     *
     * @param startY the initial tilt angle on the Y-axis.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt startY(int startY) {
        optionsBuilder.append("\"startY\": ").append(startY).append(", ");
        return this;
    }

    /**
     * Sets the perspective for the tilt effect. Lower values result in a more extreme tilt.
     *
     * @param perspective the perspective value.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt perspective(int perspective) {
        optionsBuilder.append("\"perspective\": ").append(perspective).append(", ");
        return this;
    }

    /**
     * Sets the scaling factor for the component during the tilt effect.
     *
     * @param scale the scaling factor (e.g., 1 = 100%, 2 = 200%).
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt scale(double scale) {
        optionsBuilder.append("\"scale\": ").append(scale).append(", ");
        return this;
    }

    /**
     * Sets the speed of the enter/exit transition for the tilt effect.
     *
     * @param speed the transition speed in milliseconds.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt speed(int speed) {
        optionsBuilder.append("\"speed\": ").append(speed).append(", ");
        return this;
    }

    /**
     * Enables or disables the transition on enter/exit.
     *
     * @param transition true to enable the transition, false to disable.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt transition(boolean transition) {
        optionsBuilder.append("\"transition\": ").append(transition).append(", ");
        return this;
    }

    /**
     * Sets the axis on which the tilt effect should be applied (X or Y).
     *
     * @param axis "x" or "y" to specify the axis.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt axis(String axis) {
        optionsBuilder.append("\"axis\": ").append("\"").append(axis).append("\"").append(", ");
        return this;
    }

    /**
     * Enables or disables resetting the tilt effect when the mouse leaves the component.
     *
     * @param reset true to reset the tilt on exit, false to keep the tilt state.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt reset(boolean reset) {
        optionsBuilder.append("\"reset\": ").append(reset).append(", ");
        return this;
    }

    /**
     * Sets whether the reset should go to the starting tilt angles or to [0,0].
     *
     * @param resetToStart true to reset to the starting tilt angles, false to reset to [0,0].
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt resetToStart(boolean resetToStart) {
        optionsBuilder.append("\"reset-to-start\": ").append(resetToStart).append(", ");
        return this;
    }

    /**
     * Sets the easing function for the enter/exit transition.
     *
     * @param easing the easing function.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt easing(String easing) {
        optionsBuilder.append("\"easing\": ").append("\"").append(easing).append("\"").append(", ");
        return this;
    }

    /**
     * Sets the easing function for the enter/exit transition.
     * using default `cubic-bezier(.03,.98,.52,.99)` the easing function.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt easing() {
        optionsBuilder.append("\"easing\": ").append("\"").append("cubic-bezier(.03,.98,.52,.99)").append("\"").append(", ");
        return this;
    }

    /**
     * Sets the easing function for the enter/exit transition.
     * using default `cubic-bezier(.03,.98,.52,.99)` the easing function.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt easingSmooth() {
        optionsBuilder.append("\"easing\": ").append("\"").append("cubic-bezier(.02,.08,.02,1)").append("\"").append(", ");
        return this;
    }

    /**
     * Enables or disables the glare effect on the component.
     *
     * @param glare true to enable the glare effect, false to disable.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt glare(boolean glare) {
        optionsBuilder.append("\"glare\": ").append(glare).append(", ");
        return this;
    }

    /**
     * Sets the maximum glare opacity.
     *
     * @param maxGlare the maximum glare opacity (1 = 100%, 0.5 = 50%).
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt maxGlare(double maxGlare) {
        optionsBuilder.append("\"max-glare\": ").append(maxGlare).append(", ");
        return this;
    }

    /**
     * Enables or disables pre-rendering of the glare effect elements.
     *
     * @param glarePrerender true to pre-render the glare elements, false otherwise.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt glarePrerender(boolean glarePrerender) {
        optionsBuilder.append("\"glare-prerender\": ").append(glarePrerender).append(", ");
        return this;
    }

    /**
     * Sets the CSS selector for the element that listens to mouse events for the tilt effect.
     *
     * @param cssSelector the CSS selector string.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt mouseEventElement(String cssSelector) {
        optionsBuilder.append("\"mouse-event-element\": ").append("\"").append(cssSelector).append("\"").append(", ");
        return this;
    }

    /**
     * Enables or disables full-page listening for the tilt effect.
     *
     * @param fullPageListening true to enable full-page listening, false otherwise.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt fullPageListening(boolean fullPageListening) {
        optionsBuilder.append("\"full-page-listening\": ").append(fullPageListening).append(", ");
        return this;
    }

    /**
     * Enables or disables the gyroscope detection for the tilt effect.
     *
     * @param gyroscope true to enable gyroscope detection, false to disable.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt gyroscope(boolean gyroscope) {
        optionsBuilder.append("\"gyroscope\": ").append(gyroscope).append(", ");
        return this;
    }

    /**
     * Sets the minimum angle for gyroscope detection on the X axis.
     *
     * @param minX the minimum gyroscope angle for the X axis.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt gyroscopeMinAngleX(int minX) {
        optionsBuilder.append("\"gyroscopeMinAngleX\": ").append(minX).append(", ");
        return this;
    }

    /**
     * Sets the maximum angle for gyroscope detection on the X axis.
     *
     * @param maxX the maximum gyroscope angle for the X axis.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt gyroscopeMaxAngleX(int maxX) {
        optionsBuilder.append("\"gyroscopeMaxAngleX\": ").append(maxX).append(", ");
        return this;
    }

    /**
     * Sets the minimum angle for gyroscope detection on the Y axis.
     *
     * @param minY the minimum gyroscope angle for the Y axis.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt gyroscopeMinAngleY(int minY) {
        optionsBuilder.append("\"gyroscopeMinAngleY\": ").append(minY).append(", ");
        return this;
    }

    /**
     * Sets the maximum angle for gyroscope detection on the Y axis.
     *
     * @param maxY the maximum gyroscope angle for the Y axis.
     * @return the updated {@link VanillaTilt} instance.
     */
    public VanillaTilt gyroscopeMaxAngleY(int maxY) {
        optionsBuilder.append("\"gyroscopeMaxAngleY\": ").append(maxY).append(", ");
        return this;
    }
}
