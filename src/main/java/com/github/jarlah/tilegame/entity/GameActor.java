package com.github.jarlah.tilegame.entity;

import java.awt.Graphics;

import com.github.jarlah.tilegame.maths.Vector2f;

public abstract class GameActor extends Entity {
	public GameActor(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public GameActor(float x, float y, float width, float height,
			Vector2f velocity) {
		super(x, y, width, height, velocity);
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
	}

	@Override
	public void tick() {
		super.tick();
	}
}
