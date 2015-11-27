package com.github.jarlah.tilegame.entity.tiles;

import java.awt.Graphics;

import com.github.jarlah.tilegame.camera.Camera;

public class MasterTile {

	private Tile bottom, top;
	private TileInfo tileInfo;

	public MasterTile(Tile bottom, Tile top, TileInfo tileInfo) {
		this.bottom = bottom;
		this.top = top;
		this.tileInfo = tileInfo;
	}

	public void render(Graphics g) {
		if (bottom != null) {
			bottom.render(g, tileInfo.getX() - Camera.get().getX(),
					tileInfo.getY() - Camera.get().getY(), tileInfo.getSize());
		}
		if (top != null) {
			top.render(g, tileInfo.getX() - Camera.get().getX(),
					tileInfo.getY() - Camera.get().getY(), tileInfo.getSize());
		}
	}

	public void tick() {
		if (bottom != null) {
			bottom.tick();
		}
		if (top != null) {
			top.tick();
		}
	}

	public Tile getBottom() {
		return bottom;
	}

	public Tile getTop() {
		return top;
	}

	public TileInfo getTileInfo() {
		return tileInfo;
	}
}
