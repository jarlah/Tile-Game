package com.github.jarlah.tilegame.screen;

import java.awt.Graphics;

import com.github.jarlah.tilegame.camera.Camera;
import com.github.jarlah.tilegame.entity.Player;
import com.github.jarlah.tilegame.entity.tiles.MasterTile;
import com.github.jarlah.tilegame.files.Level;

public class ScreenHandler extends Screen {
    public static ScreenHandler get() { 
        return Creator.object;
    }

    private static class Creator {
        private static final ScreenHandler object = new ScreenHandler();
    }

    private ScreenHandler() {}
    
    private Screen activeScreen;
	private Player player;

    @Override
    public void render(Graphics g) {
        if (activeScreen != null) {
            activeScreen.render(g);
        }
    }

    @Override
    public void tick(double delta) {
        if (activeScreen != null) {
        	Camera.get().setPosition(player);
            activeScreen.tick(delta);
        }
    }

	@Override
	public MasterTile getTile(int x, int y) {
		if (activeScreen != null) {
			return activeScreen.getTile(x, y);
		}
		return null;
	}

	public Screen getActiveScreen() {
		return activeScreen;
	}

	public void setActiveScreen(Screen activeScreen) {
		this.activeScreen = activeScreen;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public Level getLevel() {
		return this.activeScreen.getLevel();
	}
}
