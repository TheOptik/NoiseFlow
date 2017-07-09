package de.theoptik.noiseflow.gui;

import java.util.ArrayList;
import java.util.List;

import de.theoptik.noiseflow.flowfield.FlowField;
import de.theoptik.noiseflow.particles.Particle;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application {

	private static final Color DEFAULT_COLOR = new Color(0, 0, 0, 0.01);
	public static final int PARTICLE_COUNT = 100000;
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;

	private final List<Particle> particles = new ArrayList<>();
	private final FlowField flowField = new FlowField(WIDTH, HEIGHT, 5);
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
		final HeartBeat heartBeat = new HeartBeat(canvas, flowField, particles);
		heartBeat.start();
	}

	private void setupScene(Stage primaryStage) {
		canvas = new Canvas(WIDTH, HEIGHT);
		canvas.getGraphicsContext2D().setStroke(DEFAULT_COLOR);
		canvas.getGraphicsContext2D().setFill(DEFAULT_COLOR);
		final ControlBoard control = new ControlBoard();
		final StackPane root = new StackPane(canvas, control.getOverlay());
		root.setAlignment(Pos.TOP_RIGHT);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest((WindowEvent event) -> System.exit(0));
		primaryStage.setFullScreen(true);
		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new ScreenshotListener(canvas));
	}

	private void initializeParticles() {
		for (int i = 0; i < PARTICLE_COUNT; i++) {
			particles.add(new Particle(Math.random() * WIDTH, Math.random() * HEIGHT));
		}
	}

}
