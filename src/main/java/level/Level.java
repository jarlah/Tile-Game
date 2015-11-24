package level;

import java.awt.Graphics;
import java.util.Random;

import window.MasterCanvas;
import camera.Camera;
import entity.tiles.Grass;
import entity.tiles.MasterTile;
import entity.tiles.TileInfo;
import entity.tiles.Tree;

public class Level {
    public static Level get() { 
        return Creator.object;
    }

    private static class Creator {
        private static final Level object = new Level();
    }
    
	public static final int SCALE = 3;
	public static final int SIZE = 50;
    private final MasterTile[][] tiles;
    private final int lengthX, lengthY;
	private static final int SEED = 255;
    private final Random r = new Random(SEED);

    private Level() {
        lengthX = (MasterCanvas.WIDTH / SIZE) * SCALE;
        lengthY = (MasterCanvas.HEIGHT / SIZE) * SCALE;
        tiles = new MasterTile[lengthX][lengthY];

        for (int x = 0; x < lengthX; x++) {
            for (int y = 0; y < lengthY; y++) {
                TileInfo tileInfo = new TileInfo(x * SIZE, y * SIZE, SIZE);
                if (r.nextFloat() < 0.2f) {
                    tiles[x][y] = new MasterTile(new Grass(), new Tree(), tileInfo);
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
    	return tiles[x][y];
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

	public int getLengthX() {
		return lengthX;
	}

	public int getLengthY() {
		return lengthY;
	}
}
