package game;

import java.awt.Graphics;
import java.util.Random;

import lombok.Getter;
import screen.Screen;
import window.MasterCanvas;
import camera.Camera;
import entity.tiles.Dungeon;
import entity.tiles.Grass;
import entity.tiles.MasterTile;
import entity.tiles.TileInfo;
import entity.tiles.Tree;

@Getter
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
	
    private final MasterTile[][] tiles;
    private final Random r = new Random();

    private Plains() {
        lengthX = (MasterCanvas.WIDTH / SIZE) * SCALE;
        lengthY = (MasterCanvas.HEIGHT / SIZE) * SCALE;
        tiles = new MasterTile[lengthX][lengthY];
        boolean dungeonPlaced = false;
        for (int x = 0; x < lengthX; x++) {
            for (int y = 0; y < lengthY; y++) {
                TileInfo tileInfo = new TileInfo(x * SIZE, y * SIZE, SIZE);
                if (r.nextFloat() < 0.2f) {
                    tiles[x][y] = new MasterTile(new Grass(), new Tree(), tileInfo);
                } else if (r.nextFloat() < 0.1f && !dungeonPlaced) {
                    tiles[x][y] = new MasterTile(new Grass(), new Dungeon(), tileInfo);
                    dungeonPlaced = true;
                } else {
                    tiles[x][y] = new MasterTile(new Grass(), null, tileInfo);
                }
            }
        }
    }

    public void render(Graphics g) {
        CameraView cameraInfo = getCameraView();
        for (int x = cameraInfo.cameraX; x < cameraInfo.cameraWidth + cameraInfo.cameraX; x++) {
            for (int y = cameraInfo.cameraY; y < cameraInfo.cameraHeight + cameraInfo.cameraY; y++) {
                tiles[x][y].render(g);
            }
        }
    }

    public void tick(double delta) {
        CameraView cameraInfo = getCameraView();
        for (int x = cameraInfo.cameraX; x < cameraInfo.cameraWidth + cameraInfo.cameraX; x++) {
            for (int y = cameraInfo.cameraY; y < cameraInfo.cameraHeight + cameraInfo.cameraY; y++) {
                tiles[x][y].tick(delta);
            }
        }
    }
    
    public MasterTile getTile(int x, int y) {
    	if (x < tiles.length && y < tiles[x].length) {
    		return tiles[x][y];
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
        int cameraX = Camera.get().getX() / SIZE;
        int cameraY = Camera.get().getY() / SIZE;
        if (cameraX < 0) {
            cameraX = 0;
        }
        if (cameraY < 0) {
            cameraY = 0;
        }
        int cameraWidth = MasterCanvas.WIDTH / SIZE + 3;
        int cameraHeight = MasterCanvas.HEIGHT / SIZE + 3;
        if (cameraX > lengthX - cameraWidth) {
            cameraX = lengthX - cameraWidth;
        }
        if (cameraY > lengthY - cameraHeight) {
            cameraY = lengthY - cameraHeight;
        }
        return new CameraView(cameraX, cameraY, cameraWidth, cameraHeight);
    }
}
