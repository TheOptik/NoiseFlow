package de.theoptik.noiseflow.flowField;

import java.util.Random;

import com.flowpowered.noise.module.source.Perlin;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FlowField {

	int width;
	int height;
	int resolution;
	private final Perlin perlin;
	private final double[][] flowFieldData;
	private double z = 0;

	public FlowField(int width, int height, int resolution) {
		this.width = width;
		this.height = height;
		this.resolution = resolution;
		perlin = new Perlin();
		perlin.setSeed(new Random().nextInt());
		flowFieldData = new double[(int) Math.ceil((double) width / resolution)][(int) Math
				.ceil((double) height / resolution)];
		populateData(flowFieldData, perlin, z, resolution);
	}

	public double getValue(double x, double y) {
		return getValue((int) x, (int) y);
	}

	public double getValue(int x, int y) {
		return flowFieldData[x / resolution][y / resolution];
	}

	public void update() {
		z += 0.005;
		populateData(flowFieldData, perlin, z, resolution);
	}

	private static void populateData(double[][] values, Perlin perlin, double time, double resolution) {
		for (int x = 0; x < values.length; x++) {
			for (int y = 0; y < values[0].length; y++) {
				values[x][y] = ((perlin.getValue(x * 0.005 * resolution, y * 0.005 * resolution, time) + 1) * 128) - 32;
			}
		}
	}

	public void draw(GraphicsContext graphicsContext2D) {
		for (int x = 0; x < flowFieldData.length; x++) {
			for (int y = 0; y < flowFieldData[0].length; y++) {
				graphicsContext2D.setFill(Color.hsb(flowFieldData[x][y], 1, 1));
				graphicsContext2D.fillRect(x * resolution, y * resolution, resolution, resolution);
			}
		}
	}

}
