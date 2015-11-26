package game;

import java.awt.Color;
import java.awt.Graphics;

import screen.Screen;
import entity.tiles.MasterTile;
import files.Level;

public class Castle extends Screen {
	public static Castle get() {
		return Creator.get();
	}

	private static class Creator {
		private static Castle object;

		private static final Castle get() {
			if (object == null) {
				object = new Castle();
			}
			return object;
		}
	}

	private Castle() {}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.WHITE);
		g.drawString("Not implemented yet", 300, 300);
	}

	public void tick(double delta) {

	}

	@Override
	public MasterTile getTile(int x, int y) {
		return null;
	}

	@Override
	public Level getLevel() {
		return null;
	}
}