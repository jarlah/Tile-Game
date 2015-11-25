package entity.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import files.Assets;

public class Tile {
	private BufferedImage tile = null;

	private final String name;
	private final int spriteX;
	private final int spriteY;

	public Tile(String name, int x, int y) {
		this.name = name;
		this.spriteX = x;
		this.spriteY = y;
	}

	private BufferedImage loadTile() {
		if (tile == null) {
			tile = Assets.get().getDefaultSheet().loadSprite(name, spriteX, spriteY);
		}
		return tile;
	}

	public void render(Graphics g, int x, int y, int size) {
		g.drawImage(loadTile(), x, y, size, size, null);
	}

	public void tick(double delta) {

	}
}
