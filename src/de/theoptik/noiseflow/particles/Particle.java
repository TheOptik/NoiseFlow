package de.theoptik.noiseflow.particles;

import de.theoptik.noiseflow.flowField.FlowField;
import de.theoptik.noiseflow.test.Launcher;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Particle {

	private static final double MAX_VEL = 5;

	double x;
	double y;
	double xVel;
	double yVel;

	double px;
	double py;

	public Particle(double x, double y) {
		this.x = x;
		this.y = y;
		this.px = x;
		this.py = y;
		xVel = (Math.random() - 0.5);
		yVel = (Math.random() - 0.5);
	}

	public void update(FlowField flowField) {
		px = x;
		py = y;
		if (x <= 0) {
			x += Launcher.WIDTH;
			px = x;
		}
		if (x >= Launcher.WIDTH) {
			x -= Launcher.WIDTH;
			px = x;
		}
		if (y <= 0) {
			y += Launcher.HEIGHT;
			py = y;
		}
		if (y >= Launcher.HEIGHT) {
			y -= Launcher.HEIGHT;
			py = y;
		}
		xVel += Math.cos(Math.toRadians(flowField.getValue((int) x, (int) y)));
		yVel += Math.sin(Math.toRadians(flowField.getValue((int) x, (int) y)));

		xVel = Math.signum(xVel) * Math.min(Math.abs(xVel), MAX_VEL);
		yVel = Math.signum(yVel) * Math.min(Math.abs(yVel), MAX_VEL);

		this.x += xVel * 0.2;
		this.y += yVel * 0.2;
	}

	public void draw(GraphicsContext canvas, boolean color) {
		if (color) {
			Color c = Color.hsb(x / xVel + Math.random() * 80, 1, 1);
			canvas.setStroke(new Color(c.getRed(), c.getGreen(), c.getBlue(), 0.1));
		}
		canvas.strokeLine(px, py, x, y);
	}

}
