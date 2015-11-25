package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import level.Level;
import lombok.Getter;
import lombok.Setter;
import maths.Vector2f;
import animation.Animation;
import camera.Camera;

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
		animation.update();
		oldX = x;
		oldY = y;
		updatePosition(delta);
		checkCollision();

	}

	public void updatePosition(double delta) {
		x += delta * velocity.getX();
		y += delta * velocity.getY();
	}

	public void render(Graphics g) {
		g.drawImage(animation.getSprite(), (int) (x - Camera.get().getX()),
				(int) (y - Camera.get().getY()), (int) width, (int) height, null);
	}

	public void checkCollision() {
		boolean collisionX = false;
		boolean collisionY = false;
		
		int cellX = (int) (x / Level.SIZE);
		int cellY = (int) (y / Level.SIZE);

		if (cellX < 0 || cellY < 0 || cellX >= Level.get().getLengthX() - 1
				|| cellY > Level.get().getLengthY() - 1)
			return;

		// X
		if (velocity.getX() < 0) {
			// TOP LEFT
			collisionX = isCollision(x, y + height - 5);

			// MIDDLE LEFT
			collisionX |= isCollision(x, y + (height / 2));

			// BOTTOM LEFT
			collisionX |= isCollision(x + 5, y);

		} else if (velocity.getX() > 0) {
			// TOP RIGHT
			collisionX = isCollision(x + width, y + height - 5);

			// MIDDLE RIGHT
			collisionX |= isCollision(x + width, y + (height / 2));

			// BOTTOM RIGHT
			collisionX |= isCollision(x + width, y + 5);
		}
		if (collisionX) {
			velocity.setX(0);
			x = oldX;
		}

		// Y
		if (velocity.getY() < 0) {
			// BOTTOM LEFT
			collisionY = isCollision(x + 5, y);

			// BOTTOM MIDDLE
			collisionY |= isCollision(x + (width / 2), y);

			// BOTTOM RIGHT
			collisionY |= isCollision(x + width - 5, y);

		} else if (velocity.getY() > 0) {
			// TOP LEFT
			collisionY = isCollision(x, y + height);

			// TOP MIDDLE
			collisionY |= isCollision(x + (width / 2), y + height);

			// TOP RIGHT
			collisionY |= isCollision(x + width, y + height);

		}
		if (collisionY) {
			velocity.setY(0);
			y = oldY;
		}
	}

	public boolean isCollision(float x, float y) {
		if (Level.get().getTile((int) (x / Level.SIZE), (int) (y / Level.SIZE))
				.getTop() != null) {
			return true;
		}
		return false;
	}
}
