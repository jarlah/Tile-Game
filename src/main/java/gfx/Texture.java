package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	private final BufferedImage[] images;
	private int currentIndex = 0;

	public Texture(String link) {
		try {
			images = new BufferedImage[1];
			images[0] = ImageIO.read(Texture.class.getClassLoader().getResourceAsStream(link));
		} catch (IOException ex) {
			throw new RuntimeException("Could not load texture " + link, ex);
		}
	}

	public Texture(BufferedImage[] images) {
		this.images = images;
	}
	
	public Texture(BufferedImage images) {
		this.images = new BufferedImage[] {images};
	}

	public BufferedImage[] getImages() {
		return images;
	}

	public BufferedImage getNext() {
		return images[currentIndex];
	}
}
