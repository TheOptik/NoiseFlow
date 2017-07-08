package test;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.noise.NoiseQuality;
import com.flowpowered.noise.module.source.Perlin;

import flowField.FlowField;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import particles.Particle;

public class Launcher extends Application {

	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	double z = 0;
	private static final List<Particle> particles = new ArrayList<>();

	FlowField flowField = new FlowField(WIDTH, HEIGHT, 5);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		canvas.getGraphicsContext2D().setStroke(new Color(0, 0, 0, 0.01));
		canvas.getGraphicsContext2D().setFill(new Color(0, 0, 0, 0.01));
		VBox root = new VBox(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		primaryStage.setFullScreen(true);

		Perlin perlin = new Perlin();
		perlin.setNoiseQuality(NoiseQuality.BEST);
		perlin.setSeed((int) (Math.random() * 100000));
		for (int i = 0; i < 100000; i++) {
			particles.add(new Particle(Math.random() * WIDTH, Math.random() * HEIGHT));
		}

		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				flowField.update();
				// flowField.draw(canvas.getGraphicsContext2D());
				// canvas.getGraphicsContext2D().setFill(new Color(1, 1, 1,
				// 0.1));
				// canvas.getGraphicsContext2D().fillRect(0, 0, WIDTH, HEIGHT);
				List<Particle> copyOfParticles = new ArrayList<>();
				copyOfParticles.addAll(particles);
				for (Particle p : copyOfParticles) {
					p.update(flowField);
					p.draw(canvas.getGraphicsContext2D(), true);
				}
			}
		};
		timer.start();

	}

	public static void removeParticle(Particle particle) {
		particles.remove(particle);
	}

}
