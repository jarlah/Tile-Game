package entity.tiles;

import java.awt.Graphics;
import lombok.AllArgsConstructor;
import camera.Camera;

@AllArgsConstructor
public class MasterTile {

    private Tile bottom, top;
    private TileInfo tileInfo;

    public void render(Graphics g) {
        if (bottom != null) {
            bottom.render(g, tileInfo.getX() - Camera.O.getX(), tileInfo.getY() - Camera.O.getY(), tileInfo.getSize());
        }
        if (top != null) {
            top.render(g, tileInfo.getX() - Camera.O.getX(), tileInfo.getY() - Camera.O.getY(), tileInfo.getSize());
        }
    }

    public void tick(double delta) {
        if (bottom != null) {
            bottom.tick(delta);
        }
        if (top != null) {
            top.tick(delta);
        }
    }

}
