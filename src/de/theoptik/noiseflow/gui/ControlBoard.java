package de.theoptik.noiseflow.gui;

import static de.theoptik.noiseflow.gui.Launcher.HEIGHT;

import de.theoptik.noiseflow.flowfield.FlowField;
import de.theoptik.noiseflow.particles.Particle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ControlBoard {
	protected final BorderPane overlay;

	public ControlBoard() {
		VBox controll = new VBox();
		controll.getChildren().add(new NamedControlSlider("Max Velocity", Particle.MAX_VEL, 1, 10));
		controll.getChildren().add(new NamedControlSlider("Flow Field Influence", Particle.FIELD_INFLUENCE, 0, 1));
		controll.getChildren().add(new NamedControlSlider("Particle Opacity", Particle.PARTICLE_OPACITY, 0, 1));
		controll.getChildren().add(new NamedCheckBox("Draw Flow Field", HeartBeat.DRAW_FLOW_FIELD));
		controll.getChildren().add(
				new NamedControlSlider("Flow Field Hue", FlowField.FLOW_FIELD_HUE, 0, 1, HeartBeat.DRAW_FLOW_FIELD));
		controll.getChildren().add(new NamedControlSlider("Flow Field Saturation", FlowField.FLOW_FIELD_SATURATION, 0,
				1, HeartBeat.DRAW_FLOW_FIELD));
		controll.getChildren().add(new NamedControlSlider("Flow Field Opacity", FlowField.FLOW_FIELD_OPACITY, 0, 1,
				HeartBeat.DRAW_FLOW_FIELD));
		controll.setPadding(new Insets(HEIGHT / 10, 0, 0, 0));
		controll.setAlignment(Pos.TOP_LEFT);

		overlay = new BorderPane();
		overlay.setRight(controll);
	}

	public Node getOverlay() {
		return overlay;
	}

}
