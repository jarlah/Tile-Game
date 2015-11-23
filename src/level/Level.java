package level;

import entity.tiles.Tile;
import java.awt.Graphics;
import window.MasterCanvas;
import camera.Camera;
import entity.tiles.TileInfo;
import java.util.Random;

public class Level {

    private final Tile[][] tiles;
    private final int size = 50;
    private final int lengthX, lengthY;
    private final int scale = 2;
    private final Random r = new Random();
    
    public Level() {
        lengthX = (MasterCanvas.WIDTH / size) * scale;
        lengthY = (MasterCanvas.HEIGHT / size) * scale;
        tiles = new Tile[lengthX][lengthY];

        for (int x = 0; x < lengthX; x++) {
            for (int y = 0; y < lengthY; y++) {
                if (r.nextFloat() > 0.2f){
                    tiles[x][y] = new Tile(new TileInfo("grass", x * size, y * size, size), new Tile(new TileInfo("tree", x * size, y * size, size)));
                } else {
                    tiles[x][y] = new Tile(new TileInfo("grass", x * size, y * size, size));
                }
            }
        }
    }

    public void render(Graphics g) {
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

        for (int x = cameraX; x < cameraWidth + cameraX; x++) {
            for (int y = cameraY; y < cameraHeight + cameraY; y++) {
                tiles[x][y].render(g);
            }
        }
    }

    public void tick(double delta) {
        // TODO or not TODO
    }
}
