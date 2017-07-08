package flowField;

import com.flowpowered.noise.module.source.Perlin;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FlowField {

	int width;
	int height;
	int resolution;
	private final Perlin perlin;
	private final double[][] values;
	private double z = 0;

	public FlowField(int width, int height, int resolution) {
		this.width = width;
		this.height = height;
		this.resolution = resolution;
		perlin = new Perlin();
		values = new double[(int) Math.ceil((double) width / resolution)][(int) Math
				.ceil((double) height / resolution)];
		populateData(values, perlin, z, resolution);
	}

	public double getValue(int x, int y) {
		return values[x / resolution][y / resolution];
	}

	public void update() {
		z += 0.005;
		populateData(values, perlin, z, resolution);
	}

	private static void populateData(double[][] values, Perlin perlin, double time, double resolution) {
		for (int x = 0; x < values.length; x++) {
			for (int y = 0; y < values[0].length; y++) {
				values[x][y] = (perlin.getValue(x * 0.005 * resolution, y * 0.005 * resolution, time) + 1) * 128;
			}
		}
	}

	public void draw(GraphicsContext graphicsContext2D) {
		for (int x = 0; x < values.length; x++) {
			for (int y = 0; y < values[0].length; y++) {
				graphicsContext2D.setFill(Color.hsb(values[x][y], 1, 1));
				graphicsContext2D.fillRect(x * resolution, y * resolution, resolution, resolution);
			}
		}
	}

}
