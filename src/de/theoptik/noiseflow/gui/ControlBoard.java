package de.theoptik.noiseflow.gui;

import static de.theoptik.noiseflow.gui.Launcher.HEIGHT;

import de.theoptik.noiseflow.flowfield.FlowField;
import de.theoptik.noiseflow.particles.Particle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ControlBoard implements EventHandler<KeyEvent> {
	protected final Group overlay;

	public ControlBoard() {
		final VBox control = new VBox();
		final ObservableList<Node> children = control.getChildren();
		// Global Variables
		children.add(new NamedControlSlider("Fade", HeartBeat.FADE, 0, 0.3));
		// Particle Variables
		children.add(new NamedControlSlider("Max Velocity", Particle.MAX_VEL, 1, 10));
		children.add(new NamedControlSlider("Flow Field Influence", Particle.FIELD_INFLUENCE, 0, 1));
		children.add(new NamedControlSlider("Particle Hue", Particle.PARTICLE_HUE, 0, 1));
		children.add(new NamedControlSlider("Particle Saturation", Particle.PARTICLE_SATURATION, 0, 1));
		children.add(new NamedControlSlider("Particle Opacity", Particle.PARTICLE_OPACITY, 0, 1));
		// Flow Field Variables
		children.add(new NamedControlSlider("Flow Field Update Speed", FlowField.FLOW_UPDATE_SPEED, 0, 0.01));
		children.add(new NamedControlSlider("Flow Field Bias", FlowField.FLOW_FIELD_BIAS, 0, 128));
		children.add(new NamedCheckBox("Draw Flow Field", HeartBeat.DRAW_FLOW_FIELD));
		children.add(new NamedControlSlider("Flow Field Hue", FlowField.FLOW_FIELD_HUE, 0, 1, HeartBeat.DRAW_FLOW_FIELD));
		children.add(new NamedControlSlider("Flow Field Saturation", FlowField.FLOW_FIELD_SATURATION, 0, 1, HeartBeat.DRAW_FLOW_FIELD));
		children.add(new NamedControlSlider("Flow Field Opacity", FlowField.FLOW_FIELD_OPACITY, 0, 1, HeartBeat.DRAW_FLOW_FIELD));

		control.setPadding(new Insets(HEIGHT / 10, 0, 0, 0));
		control.setAlignment(Pos.TOP_LEFT);

		overlay = new Group();
		final Pane background = new Pane();
		background.setStyle("-fx-background-color: rgba(255,255,255,0.8);");// TODO proper styling
		background.prefWidthProperty().bind(control.widthProperty());
		background.translateYProperty().set(control.getPadding().getTop());
		background.prefHeightProperty().bind(control.heightProperty().subtract(control.getPadding().getTop()));
		overlay.getChildren().add(background);
		overlay.getChildren().add(control);
	}

	public Node getOverlay() {
		return overlay;
	}

    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.M){
            overlay.setVisible(!overlay.isVisible());
        }
    }
}
