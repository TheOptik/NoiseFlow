package de.theoptik.noiseflow.gui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.layout.Region;

public class AllignedDualNode extends Group {

	protected static final DoubleProperty VERTICAL_ALLIGNMENT = new SimpleDoubleProperty(0);

	protected void setNodes(Region left, Region right) {
		if (left.widthProperty().get() > VERTICAL_ALLIGNMENT.get()) {
			VERTICAL_ALLIGNMENT.set(left.widthProperty().get());
			System.out.println(VERTICAL_ALLIGNMENT.get());
		}
		this.getChildren().addAll(left, right);
	}
}
