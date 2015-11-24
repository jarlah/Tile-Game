package entity;

import java.awt.Graphics;

public abstract class GameActor extends Entity {
	public GameActor(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
	}
	
	@Override
	public void tick(double delta) {
		super.tick(delta);
	}
}
