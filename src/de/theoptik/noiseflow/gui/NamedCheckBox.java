package de.theoptik.noiseflow.gui;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class NamedCheckBox extends Group {

	protected final String name;
	protected final BooleanProperty boundProperty;
	protected final CheckBox checkBox;
	protected final Label label;

	public NamedCheckBox(String name, BooleanProperty boundProperty) {
		this.name = name;
		this.boundProperty = boundProperty;
		this.checkBox = new CheckBox();
		label = new Label(name.concat(": "));
		checkBox.translateXProperty().bind(label.widthProperty());
		checkBox.setSelected(boundProperty.get());
		boundProperty.bind(checkBox.selectedProperty());
		this.getChildren().add(checkBox);
		this.getChildren().add(label);
	}

}
