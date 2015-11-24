package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import lombok.Getter;
import maths.Vector2f;

@Getter
public abstract class Entity {
    float x, y;
    private final float width, height;
    private final Vector2f velocity;
    private final Rectangle boundArea;

    public Entity(float x, float y, float width, float height) {
        this(x, y, width, height, new Vector2f());
    }

    public Entity(float x, float y, float width, float height, Vector2f velocity) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundArea = new Rectangle((int)x, (int)y, (int)width, (int)height);
        this.velocity = velocity;
    }

    public abstract void render(Graphics g);

    public abstract void tick(double delta);
}