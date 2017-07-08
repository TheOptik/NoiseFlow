package de.theoptik.noiseflow.particles;

import java.util.Random;

import de.theoptik.noiseflow.flowfield.FlowField;
import de.theoptik.noiseflow.gui.Launcher;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Particle {

	public static final DoubleProperty FIELD_INFLUENCE = new SimpleDoubleProperty(0.1);
	public static final DoubleProperty PARTICLE_OPACITY = new SimpleDoubleProperty(1);
	public static final DoubleProperty MAX_VEL = new SimpleDoubleProperty(5);
	private static final Random RANDOM = new Random();

	protected double x;
	protected double y;
	protected double previousX;
	protected double previousY;
	protected double xVel;
	protected double yVel;

	public Particle(double x, double y) {
		this.x = x;
		this.y = y;
		this.previousX = x;
		this.previousY = y;
		xVel = getRandomVelocity();
		yVel = getRandomVelocity();
	}

	private double getRandomVelocity() {
		return RANDOM.nextDouble() - 0.5;
	}

	public void update(FlowField flowField) {
		keepParticlesInBoundry();
		updateVelocity(flowField);
		updatePreviousPosition();
		updatePosition();
	}

	private void updatePreviousPosition() {
		previousX = x;
		previousY = y;
	}

	private void updatePosition() {
		this.x += xVel * 0.2;
		this.y += yVel * 0.2;
	}

	private void updateVelocity(FlowField flowField) {
		xVel += Math.cos(Math.toRadians(flowField.getValue(x, y))) * FIELD_INFLUENCE.get();
		yVel += Math.sin(Math.toRadians(flowField.getValue(x, y))) * FIELD_INFLUENCE.get();

		xVel = Math.signum(xVel) * Math.min(Math.abs(xVel), MAX_VEL.get());
		yVel = Math.signum(yVel) * Math.min(Math.abs(yVel), MAX_VEL.get());
	}

	private void keepParticlesInBoundry() {
		if (x <= 0) {
			x += Launcher.WIDTH;
		}
		if (x >= Launcher.WIDTH) {
			x -= Launcher.WIDTH;
		}
		if (y <= 0) {
			y += Launcher.HEIGHT;
		}
		if (y >= Launcher.HEIGHT) {
			y -= Launcher.HEIGHT;
		}
	}

	public void draw(GraphicsContext canvas, boolean colorMode) {
		if (colorMode) {
			Color c = Color.hsb(Math.toDegrees(Math.atan2(yVel, xVel)), 1, 1, PARTICLE_OPACITY.get());
			canvas.setStroke(c);
		}
		canvas.strokeLine(previousX, previousY, x, y);
	}

}
