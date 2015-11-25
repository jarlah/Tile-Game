package entity.tiles;

import java.awt.Graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import camera.Camera;

@Getter
@AllArgsConstructor
public class MasterTile {

    private Tile bottom, top;
    private TileInfo tileInfo;

    public void render(Graphics g) {
        if (bottom != null) {
            bottom.render(g, tileInfo.getX() - Camera.get().getX(), tileInfo.getY() - Camera.get().getY(), tileInfo.getSize());
        }
        if (top != null) {
            top.render(g, tileInfo.getX() - Camera.get().getX(), tileInfo.getY() - Camera.get().getY(), tileInfo.getSize());
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
