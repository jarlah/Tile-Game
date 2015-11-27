package com.github.jarlah.tilegame.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.github.jarlah.tilegame.animation.Animation;
import com.github.jarlah.tilegame.camera.Camera;
import com.github.jarlah.tilegame.entity.tiles.MasterTile;
import com.github.jarlah.tilegame.entity.tiles.Tile;
import com.github.jarlah.tilegame.files.Level;
import com.github.jarlah.tilegame.maths.Vector2f;
import com.github.jarlah.tilegame.screen.ScreenHandler;

public abstract class Entity {
	float x, y;
	private final float width, height;
	private final Vector2f velocity;
	private float oldX, oldY;
	private Rectangle boundArea;
	private Animation animation;
	
	public Entity(float x, float y, float width, float height) {
		this(x, y, width, height, new Vector2f());
	}

	public Entity(float x, float y, float width, float height, Vector2f velocity) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.boundArea = new Rectangle((int) x, (int) y, (int) width, (int) height);
		this.velocity = velocity;
	}

	public void tick(double delta) {
		oldX = x;
		oldY = y;
		updatePosition(delta);
		boolean collision = checkCollision();
		if (collision) {
			animation.stop();
			animation.reset();
		}else {
			animation.update();
		}
	}

	public void updatePosition(double delta) {
		x += delta * velocity.getX();
		y += delta * velocity.getY();
	}

	public void render(Graphics g) {
		g.drawImage(animation.getSprite(), (int) (x - Camera.get().getX()),
				(int) (y - Camera.get().getY()), (int) width, (int) height, null);
	}

	public boolean checkCollision() {
		Tile collisionX = null;
		Tile collisionY = null;
		
		int cellX = (int) (x / 50);
		int cellY = (int) (y / 50);

		final Level level = ScreenHandler.get().getActiveScreen().getLevel();
		
		if (level == null) {
			return false;
		}
		
		if (cellX < 0 || cellY < 0 || cellX >= level.getLengthX() - 1 || cellY > level.getLengthY() - 1)
			return false;

		// X
		if (velocity.getX() < 0) {
			// TOP LEFT
			collisionX = isCollision(x, y + height - 5);

			// MIDDLE LEFT
			collisionX = collisionX != null ? collisionX : isCollision(x, y + (height / 2));

			// BOTTOM LEFT
			collisionX = collisionX != null ? collisionX : isCollision(x + 5, y);

		} else if (velocity.getX() > 0) {
			// TOP RIGHT
			collisionX = isCollision(x + width, y + height - 5);

			// MIDDLE RIGHT
			collisionX = collisionX != null ? collisionX : isCollision(x + width, y + (height / 2));

			// BOTTOM RIGHT
			collisionX = collisionX != null ? collisionX : isCollision(x + width, y + 5);
		}

		if (collisionX != null) {
			velocity.setX(0);
			x = oldX;
		}

		// Y
		if (velocity.getY() < 0) {
			// BOTTOM LEFT
			collisionY = isCollision(x + 5, y);

			// BOTTOM MIDDLE
			collisionY = collisionY != null ? collisionY : isCollision(x + (width / 2), y);

			// BOTTOM RIGHT
			collisionY = collisionY != null ? collisionY : isCollision(x + width - 5, y);

		} else if (velocity.getY() > 0) {
			// TOP LEFT
			collisionY = isCollision(x, y + height);

			// TOP MIDDLE
			collisionY = collisionY != null ? collisionY : isCollision(x + (width / 2), y + height);

			// TOP RIGHT
			collisionY = collisionY != null ? collisionY : isCollision(x + width, y + height);

		}
		if (collisionY != null) {
			velocity.setY(0);
			y = oldY;
		}
		
		handleCollision(collisionX, collisionY);

		return collisionX != null || collisionY != null;
	}

	public abstract void handleCollision(Tile collisionX, Tile collisionY);

	public Tile isCollision(float x, float y) {
		MasterTile tile = ScreenHandler.get().getActiveScreen().getTile((int) (x / 50), (int) (y / 50));
		return tile != null ? tile.getTop() : null;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getOldX() {
		return oldX;
	}

	public void setOldX(float oldX) {
		this.oldX = oldX;
	}

	public float getOldY() {
		return oldY;
	}

	public void setOldY(float oldY) {
		this.oldY = oldY;
	}

	public Rectangle getBoundArea() {
		return boundArea;
	}

	public void setBoundArea(Rectangle boundArea) {
		this.boundArea = boundArea;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public Vector2f getVelocity() {
		return velocity;
	}
}
