package entity;

import gfx.Texture;

import java.awt.Graphics;

public abstract class GameActor extends Entity {
	public GameActor(Texture texture, float x, float y, float width, float height) {
		super(texture, x, y, width, height);
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
