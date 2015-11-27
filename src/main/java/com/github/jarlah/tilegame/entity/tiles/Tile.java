package com.github.jarlah.tilegame.entity.tiles;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.github.jarlah.tilegame.animation.Animation;

public class Tile {
	public static final int FRAME_DELAY = 10;
	public static final int TILE_SIZE = 16;

	private Rectangle boundArea;
	private Animation animation;
	private final int x, y, size;

	public Tile(TileInfo tileInfo, int size) {
		this(tileInfo.getX(), tileInfo.getY(), size);
	}

	public Tile(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		boundArea = new Rectangle(x, y, size, size);
	}

	public void render(Graphics g, int x, int y, int size) {
		g.drawImage(animation.getSprite(), x, y, size, size, null);
	}

	public void tick() {
		// update animation
	}

	public Rectangle getBoundArea() {
		return boundArea;
	}

	public void setBoundArea(Rectangle boundArea) {
		this.boundArea = boundArea;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSize() {
		return size;
	}
}
