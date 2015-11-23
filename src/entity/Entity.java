package entity;

import java.awt.Graphics;

public abstract class Entity {
    public abstract void render(Graphics g);
    public abstract void tick(long delta);
//    protected final Rectangle boundArea;
//    protected final int x,y,width,height;
//    
//    public Entity(int x, int y, int width, int height) {
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
//        boundArea = new Rectangle(x,y,width,height);
//    }
}
