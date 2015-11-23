package level;

import entity.tiles.Tile;
import java.awt.Graphics;
import window.MasterCanvas;

public class Map {
    
    private final Tile[][] tiles;
    private final int size = 50;
    private final int lengthX, lengthY;
    
    public Map() {
        lengthX = MasterCanvas.WIDTH/size;
        lengthY = MasterCanvas.HEIGHT/size;
        tiles = new Tile[lengthX][lengthY];
        
        for(int x = 0; x < lengthX; x++) {
            for (int y = 0; y < lengthY; y++) {
                tiles[x][y] = new Tile("grass", x*size, y*size, size, size);
            }
        }
    }
    
    public void render(Graphics g) {
        for(int x = 0; x < lengthX; x++) {
            for (int y = 0; y < lengthY; y++) {
                tiles[x][y].render(g);
            }
        }
    }
    
    public void tick(double delta) {
        
    }
}
