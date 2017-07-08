package de.theoptik.noiseflow.gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class NamedControlSlider extends Group {

	protected final String name;
	protected final DoubleProperty boundProperty;
	protected final Slider slider;
	protected final Label label;

	public NamedControlSlider(String name, DoubleProperty boundProperty, double min, double max) {
		this(name, boundProperty, min, max, new SimpleBooleanProperty(true));
	}

	public NamedControlSlider(String name, DoubleProperty boundProperty, double min, double max, BooleanProperty enabled) {
		this.name = name;
		this.boundProperty = boundProperty;
		slider = new Slider();
		this.disableProperty().bind(enabled.not());
		label = new Label(name.concat(": "));
		slider.translateXProperty().bind(label.widthProperty());
		slider.setMin(min);
		slider.setMax(max);
		slider.setValue(boundProperty.get());
		boundProperty.bind(slider.valueProperty());
		this.getChildren().add(slider);
		this.getChildren().add(label);
	}

}
