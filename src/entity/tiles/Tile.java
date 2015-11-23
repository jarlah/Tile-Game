package entity.tiles;

import entity.Entity;
import files.Assets;
import java.awt.Graphics;
import camera.Camera;

public class Tile extends Entity {
    private final TileInfo tileInfo;
    private final Tile top;
    
    public Tile(TileInfo tileInfo) {
        this.tileInfo = tileInfo;
        this.top = null;
    }
    
    public Tile(TileInfo tileInfo, Tile top) {
        this.tileInfo = tileInfo;
        this.top = top;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.Singleton.defaultSheet.getImage(tileInfo.getName()), tileInfo.getX()-Camera.Singleton.getX(), tileInfo.getY()-Camera.Singleton.getY(), tileInfo.getSize(), tileInfo.getSize(), null);
        if (top != null) {
            g.drawImage(Assets.Singleton.defaultSheet.getImage(top.tileInfo.getName()), top.tileInfo.getX()-Camera.Singleton.getX(), top.tileInfo.getY()-Camera.Singleton.getY(), top.tileInfo.getSize(), top.tileInfo.getSize(), null);
        }
    }

    @Override
    public void tick(long delta) {
        // nothing atm
    }
    
}
