package com.github.jarlah.tilegame.entity.tiles;

public class TileInfo {
	private final int x, y, size;

	public TileInfo(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
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
