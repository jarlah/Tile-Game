package game;

import java.awt.Graphics;

import screen.Screen;
import entity.tiles.MasterTile;

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

	public void render(Graphics g) {

	}

	public void tick(double delta) {

	}

	@Override
	public MasterTile getTile(int x, int y) {
		return null;
	}
}