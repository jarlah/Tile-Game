package screen;

import java.awt.Graphics;

import entity.tiles.MasterTile;
import files.Level;

public abstract class Screen {
	public abstract void render(Graphics g);

	public abstract void tick(double delta);

	public abstract MasterTile getTile(int x, int y);
	
	public abstract Level getLevel();
}
