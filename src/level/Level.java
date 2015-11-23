package level;

import entity.tiles.MasterTile;
import java.awt.Graphics;
import window.MasterCanvas;
import camera.Camera;
import entity.tiles.Grass;
import entity.tiles.TileInfo;
import entity.tiles.Tree;
import java.util.Random;

public class Level {

    private final MasterTile[][] tiles;
    private final int size = 50;
    private final int lengthX, lengthY;
    private final int scale = 3;
    private final Random r = new Random();

    public Level() {
        lengthX = (MasterCanvas.WIDTH / size) * scale;
        lengthY = (MasterCanvas.HEIGHT / size) * scale;
        tiles = new MasterTile[lengthX][lengthY];

        for (int x = 0; x < lengthX; x++) {
            for (int y = 0; y < lengthY; y++) {
                TileInfo tileInfo = new TileInfo(x * size, y * size, size);
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

    private class CameraView {

        private final int cameraX, cameraY, cameraWidth, cameraHeight;

        CameraView(int x, int y, int cw, int ch) {
            this.cameraX = x;
            this.cameraY = y;
            this.cameraWidth = cw;
            this.cameraHeight = ch;
        }

    }

    private CameraView getCameraView() {
        int cameraX = Camera.Singleton.getX() / size;
        int cameraY = Camera.Singleton.getY() / size;

        if (cameraX < 0) {
            cameraX = 0;
        }
        if (cameraY < 0) {
            cameraY = 0;
        }

        int cameraWidth = MasterCanvas.WIDTH / size + 3;
        int cameraHeight = MasterCanvas.HEIGHT / size + 3;

        if (cameraX > lengthX - cameraWidth) {
            cameraX = lengthX - cameraWidth;
        }
        if (cameraY > lengthY - cameraHeight) {
            cameraY = lengthY - cameraHeight;
        }

        return new CameraView(cameraX, cameraY, cameraWidth, cameraHeight);
    }
}
