package de.theoptik.noiseflow.gui;

import java.util.ArrayList;
import java.util.List;

import de.theoptik.noiseflow.flowField.FlowField;
import de.theoptik.noiseflow.particles.Particle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application {

	private static final Color DEFAULT_COLOR = new Color(0, 0, 0, 0.01);
	public static final int PARTICLE_COUNT = 100000;
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;

	private final List<Particle> particles = new ArrayList<>();
	private FlowField flowField = new FlowField(WIDTH, HEIGHT, 5);
	private Canvas canvas;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setupScene(primaryStage);
		initializeParticles();
		startGameLoop();
	}

	private void startGameLoop() {
		HeartBeat heartBeat = new HeartBeat(canvas, flowField, particles);
		heartBeat.start();
	}

	private void setupScene(Stage primaryStage) {
		canvas = new Canvas(WIDTH, HEIGHT);
		canvas.getGraphicsContext2D().setStroke(DEFAULT_COLOR);
		canvas.getGraphicsContext2D().setFill(DEFAULT_COLOR);
		VBox root = new VBox(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		primaryStage.setOnCloseRequest((WindowEvent event) -> System.exit(0));
		primaryStage.setFullScreen(true);
	}

	private void initializeParticles() {
		for (int i = 0; i < PARTICLE_COUNT; i++) {
			particles.add(new Particle(Math.random() * WIDTH, Math.random() * HEIGHT));
		}
	}

}