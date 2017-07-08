package de.theoptik.noiseflow.gui;

import static de.theoptik.noiseflow.gui.Launcher.HEIGHT;
import static de.theoptik.noiseflow.gui.Launcher.WIDTH;

import java.util.ArrayList;
import java.util.List;

import de.theoptik.noiseflow.flowField.FlowField;
import de.theoptik.noiseflow.particles.Particle;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class HeartBeat extends AnimationTimer {
	public static final BooleanProperty drawFlowField = new SimpleBooleanProperty(false);
	private static final Color BACKGROUND_COLOR = new Color(0.1, 0.1, 0.1, 0.2);

	private final Canvas canvas;
	private final FlowField flowField;
	private final List<Particle> particles;

	public HeartBeat(Canvas canvas, FlowField flowField, List<Particle> particles) {
		this.canvas = canvas;
		this.flowField = flowField;
		this.particles = particles;
	}

	@Override
	public void handle(long now) {
		flowField.update();
		if (drawFlowField.get()) {
			flowField.draw(canvas.getGraphicsContext2D());
		}
		canvas.getGraphicsContext2D().setFill(BACKGROUND_COLOR);
		canvas.getGraphicsContext2D().fillRect(0, 0, WIDTH, HEIGHT);

		List<Particle> copyOfParticles = new ArrayList<>();
		copyOfParticles.addAll(particles);
		for (Particle p : copyOfParticles) {
			p.update(flowField);
			p.draw(canvas.getGraphicsContext2D(), true);
		}
	}
}
