package de.theoptik.noiseflow.flowfield;

import java.util.Random;

import com.flowpowered.noise.module.source.Perlin;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FlowField {

	public static final DoubleProperty FLOW_FIELD_HUE = new SimpleDoubleProperty(1);
	public static final DoubleProperty FLOW_FIELD_SATURATION = new SimpleDoubleProperty(1);
	public static final DoubleProperty FLOW_FIELD_OPACITY = new SimpleDoubleProperty(0.3);
	public static final DoubleProperty FLOW_UPDATE_SPEED = new SimpleDoubleProperty(0.005);
	public static final DoubleProperty FLOW_FIELD_BIAS = new SimpleDoubleProperty(64);

	private int resolution;
	private final Perlin perlin;
	private final double[][] flowFieldData;
	private double z = 0;

	public FlowField(int width, int height, int resolution) {
		this.resolution = resolution;
		perlin = new Perlin();
		perlin.setSeed(new Random().nextInt());
		flowFieldData = new double[(int) Math.ceil((double) width / resolution)][(int) Math.ceil((double) height / resolution)];
		populateData(flowFieldData, perlin, z, resolution);
	}

	public double getValue(double x, double y) {
		return getValue((int) x, (int) y);
	}

	public double getValue(int x, int y) {
		return flowFieldData[x / resolution][y / resolution];
	}

	public void update() {
		z += FLOW_UPDATE_SPEED.get();
		populateData(flowFieldData, perlin, z, resolution);
	}

	private static void populateData(double[][] values, Perlin perlin, double time, double resolution) {
		for (int x = 0; x < values.length; x++) {
			for (int y = 0; y < values[0].length; y++) {
				values[x][y] = (perlin.getValue(x * 0.005 * resolution, y * 0.005 * resolution, time) + 1) * 128 - FLOW_FIELD_BIAS.get();
			}
		}
	}

	public void draw(GraphicsContext graphicsContext2D) {
		for (int x = 0; x < flowFieldData.length; x++) {
			for (int y = 0; y < flowFieldData[0].length; y++) {
				graphicsContext2D
						.setFill(Color.hsb(flowFieldData[x][y], FLOW_FIELD_HUE.get(), FLOW_FIELD_SATURATION.get(), FLOW_FIELD_OPACITY.get()));
				graphicsContext2D.fillRect((double) x * resolution, (double) y * resolution, resolution, resolution);
			}
		}
	}

}
