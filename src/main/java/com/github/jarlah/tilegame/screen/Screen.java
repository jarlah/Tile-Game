package com.github.jarlah.tilegame.screen;

import java.awt.Graphics;

import com.github.jarlah.tilegame.entity.tiles.MasterTile;
import com.github.jarlah.tilegame.files.Level;

public abstract class Screen {
	public abstract void render(Graphics g);

	public abstract void tick(double delta);

	public abstract MasterTile getTile(int x, int y);
	
	public abstract Level getLevel();
}
