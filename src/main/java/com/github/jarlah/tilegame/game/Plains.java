package com.github.jarlah.tilegame.game;

import java.awt.Graphics;

import com.github.jarlah.tilegame.camera.Camera;
import com.github.jarlah.tilegame.entity.tiles.MasterTile;
import com.github.jarlah.tilegame.files.Level;
import com.github.jarlah.tilegame.screen.Screen;
import com.github.jarlah.tilegame.window.MasterCanvas;

public class Plains extends Screen {
    public static Plains get() { 
        return Creator.get();
    }

    private static class Creator {
    	private static Plains object;
    	
        private static final Plains get() {
        	if (object == null){
        		object = new Plains();
        	}
        	return object;
        }
    }
    
    public static final int SCALE = 3;

    private final Level level = new Level("Levels/Plains.csv");

    private Plains() {}

    public void render(Graphics g) {
        CameraView cameraInfo = getCameraView();
        for (int x = cameraInfo.cameraX; x < cameraInfo.cameraWidth + cameraInfo.cameraX; x++) {
            for (int y = cameraInfo.cameraY; y < cameraInfo.cameraHeight + cameraInfo.cameraY; y++) {
            	level.getTiles()[x][y].render(g);
            }
        }
    }

    public void tick(double delta) {
        CameraView cameraInfo = getCameraView();
        for (int x = cameraInfo.cameraX; x < cameraInfo.cameraWidth + cameraInfo.cameraX; x++) {
            for (int y = cameraInfo.cameraY; y < cameraInfo.cameraHeight + cameraInfo.cameraY; y++) {
            	level.getTiles()[x][y].tick(delta);
            }
        }
    }
    
    public MasterTile getTile(int x, int y) {
    	if (x < level.getTiles().length && y < level.getTiles()[x].length) {
    		return level.getTiles()[x][y];
    	}
    	return null;
    }

    private class CameraView {
    	final int cameraX, cameraY, cameraWidth, cameraHeight;
        CameraView(int x, int y, int cw, int ch) {
            this.cameraX = x;
            this.cameraY = y;
            this.cameraWidth = cw;
            this.cameraHeight = ch;
        }
    }

    private CameraView getCameraView() {
        int cameraX = Camera.get().getX() / level.getSize();
        int cameraY = Camera.get().getY() / level.getSize();
        if (cameraX < 0) {
            cameraX = 0;
        }
        if (cameraY < 0) {
            cameraY = 0;
        }
        int cameraWidth = MasterCanvas.WIDTH / level.getSize() + 3;
        int cameraHeight = MasterCanvas.HEIGHT / level.getSize() + 3;
        if (cameraX > level.getLengthX() - cameraWidth) {
            cameraX = level.getLengthX() - cameraWidth;
        }
        if (cameraY > level.getLengthY() - cameraHeight) {
            cameraY = level.getLengthY() - cameraHeight;
        }
        return new CameraView(cameraX, cameraY, cameraWidth, cameraHeight);
    }

	public Level getLevel() {
		return level;
	}
}
