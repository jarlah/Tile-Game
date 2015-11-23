package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import lombok.Getter;

@Getter
public abstract class Entity {
    public abstract void render(Graphics g);
    public abstract void tick(double delta);
    private final Rectangle boundArea;
    int x,y,width,height;
    
    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        boundArea = new Rectangle(x,y,width,height);
    }
}
