package de.theoptik.noiseflow.gui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ScreenshotListener implements EventHandler<KeyEvent> {

	private final Canvas canvas;

	public ScreenshotListener(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void handle(KeyEvent event) {

		if (event.getCode() == KeyCode.PRINTSCREEN) {
			WritableImage image = canvas.snapshot(null, null);
			try {
				String fileName = System.getProperty("user.dir") + "/screenshot_" + System.currentTimeMillis() + ".png";
				ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", new File(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
