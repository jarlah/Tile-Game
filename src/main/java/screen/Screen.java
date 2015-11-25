package screen;

import java.awt.Graphics;

import entity.tiles.MasterTile;
import lombok.Getter;

@Getter
public abstract class Screen {
    public abstract void render(Graphics g);
    public abstract void tick(double delta);
    protected int lengthX, lengthY;
    public static final int SIZE = 50;
    public abstract MasterTile getTile(int x, int y);
}
