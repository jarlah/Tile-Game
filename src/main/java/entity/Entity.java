package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import lombok.Getter;
import lombok.Setter;
import maths.Vector2f;
import screen.Screen;
import screen.ScreenHandler;
import animation.Animation;
import camera.Camera;
import entity.tiles.Dungeon;
import entity.tiles.MasterTile;
import entity.tiles.Tile;
import game.Castle;

@Getter
@Setter
public abstract class Entity {
	float x, y;
	private final float width, height;
	private final Vector2f velocity;
	private final Rectangle boundArea;
	private Animation animation;
	private float oldX, oldY;

	public Entity(float x, float y, float width, float height) {
		this(x, y, width, height, new Vector2f());
	}

	public Entity(float x, float y, float width, float height, Vector2f velocity) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.boundArea = new Rectangle((int) x, (int) y, (int) width,
				(int) height);
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
		
		int cellX = (int) (x / Screen.SIZE);
		int cellY = (int) (y / Screen.SIZE);

		if (cellX < 0 || cellY < 0 || cellX >= ScreenHandler.get().getActiveScreen().getLengthX() - 1
				|| cellY > ScreenHandler.get().getActiveScreen().getLengthY() - 1)
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

		if (collisionX instanceof Dungeon || collisionY instanceof Dungeon) {
			ScreenHandler.get().setActiveScreen(Castle.get());
		}

		return collisionX != null || collisionY != null;
	}

	public Tile isCollision(float x, float y) {
		MasterTile tile = ScreenHandler.get().getActiveScreen().getTile((int) (x / Screen.SIZE), (int) (y / Screen.SIZE));
		return tile != null ? tile.getTop() : null;
	}
}
