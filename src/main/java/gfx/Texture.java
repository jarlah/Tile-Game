package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class Texture {
	private final BufferedImage[] images;
	private int currentIndex = 0;

	public Texture(String link) {
		try {
			images = new BufferedImage[1];
			images[0] = ImageIO.read(Texture.class.getClassLoader()
					.getResourceAsStream(link));
		} catch (IOException ex) {
			throw new RuntimeException("Could not load texture " + link, ex);
		}
	}

	Runnable periodicTask = new Runnable() {
		public void run() {
			if (currentIndex < images.length - 1) {
				currentIndex++;
			} else {
				currentIndex = 0;
			}
		}
	};

	public Texture(BufferedImage[] images) {
		this.images = images;
		ScheduledExecutorService executor = Executors
				.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(periodicTask, 0, 500,
				TimeUnit.MILLISECONDS);
	}

	public BufferedImage[] getImages() {
		return images;
	}

	public BufferedImage getNext() {
		return images[currentIndex];
	}
}
