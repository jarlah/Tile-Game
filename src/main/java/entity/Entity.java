package entity;

import gfx.Texture;

import java.awt.Graphics;
import java.awt.Rectangle;

import level.Level;
import maths.Vector2f;
import camera.Camera;

public abstract class Entity {
	float x, y;
	private final float width, height;
	private final Vector2f velocity;
	private final Rectangle boundArea;
	private Texture texture;
	private float oldX, oldY;

	public Entity(Texture texture, float x, float y, float width, float height) {
		this(texture, x, y, width, height, new Vector2f());
	}

	public Entity(Texture texture, float x, float y, float width, float height,
			Vector2f velocity) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.boundArea = new Rectangle((int) x, (int) y, (int) width,
				(int) height);
		this.velocity = velocity;
		this.texture = texture;
	}

	public void tick(double delta) {
		oldX = x;
		oldY = y;
		updatePosition(delta);
		checkCollision();
	}

	public void updatePosition(double delta) {
		x += delta * getVelocity().getX();
		y += delta * getVelocity().getY();
	}

	public void render(Graphics g) {
		g.drawImage(texture.getImage(), (int) (getX() - Camera.get().getX()),
				(int) (getY() - Camera.get().getY()), (int) getWidth(),
				(int) getHeight(), null);
	}

	public void checkCollision() {
		boolean collisionX = false;
		boolean collisionY = false;
		int cellX = (int) (getX() / Level.SIZE);
		int cellY = (int) (getY() / Level.SIZE);

		if (cellX < 0 || cellY < 0 || cellX >= Level.get().getLengthX() - 1
				|| cellY > Level.get().getLengthY() - 1)
			return;

		// X
		if (getVelocity().getX() < 0) {
			// TOP LEFT
			collisionX = isCollision(getX(), getY() + getHeight() - 5);

			// MIDDLE LEFT
			collisionX |= isCollision(getX(), getY() + (getHeight() / 2));

			// BOTTOM LEFT
			collisionX |= isCollision(getX() + 5, getY());

		} else if (getVelocity().getX() > 0) {
			// TOP RIGHT
			collisionX = isCollision(getX() + getWidth(), getY()
					+ getHeight() - 5);

			// MIDDLE RIGHT
			collisionX |= isCollision(getX() + getWidth(), getY()
					+ (getHeight() / 2));

			// BOTTOM RIGHT
			collisionX |= isCollision(getX() + getWidth(), getY() + 5);
		}
		if (collisionX) {
			getVelocity().setX(0);
			x = oldX;
		}

		// Y
		if (getVelocity().getY() < 0) {
			// BOTTOM LEFT
			collisionY = isCollision(getX() + 5, getY());

			// BOTTOM MIDDLE
			collisionY |= isCollision(getX() + (getWidth() / 2), getY());

			// BOTTOM RIGHT
			collisionY |= isCollision(getX() + getWidth() - 5, getY());

		} else if (getVelocity().getY() > 0) {
			// TOP LEFT
			collisionY = isCollision(getX(), getY() + getHeight());

			// TOP MIDDLE
			collisionY |= isCollision(getX() + (getWidth() / 2), getY()
					+ getHeight());

			// TOP RIGHT
			collisionY |= isCollision(getX() + getWidth(), getY()
					+ getHeight());

		}
		if (collisionY) {
			getVelocity().setY(0);
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

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public Vector2f getVelocity() {
		return velocity;
	}

	public Rectangle getBoundArea() {
		return boundArea;
	}
}
