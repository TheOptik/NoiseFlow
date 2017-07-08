package de.theoptik.noiseflow.gui;

import static de.theoptik.noiseflow.gui.Launcher.HEIGHT;
import static de.theoptik.noiseflow.gui.Launcher.WIDTH;

import java.util.ArrayList;
import java.util.List;

import de.theoptik.noiseflow.flowfield.FlowField;
import de.theoptik.noiseflow.particles.Particle;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class HeartBeat extends AnimationTimer {
	public static final BooleanProperty DRAW_FLOW_FIELD = new SimpleBooleanProperty(false);
	public static final DoubleProperty FADE = new SimpleDoubleProperty(0.8);

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
		canvas.getGraphicsContext2D().setFill(new Color(0.1, 0.1, 0.1, FADE.get()));
		canvas.getGraphicsContext2D().fillRect(0, 0, WIDTH, HEIGHT);
		flowField.update();
		if (DRAW_FLOW_FIELD.get()) {
			flowField.draw(canvas.getGraphicsContext2D());
		}

		final List<Particle> copyOfParticles = new ArrayList<>();
		copyOfParticles.addAll(particles);
		for (final Particle p : copyOfParticles) {
			p.update(flowField);
			p.draw(canvas.getGraphicsContext2D(), true);
		}
	}
}
