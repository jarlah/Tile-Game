package entity;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import camera.Camera;
import files.Assets;

public class Player extends Entity {

	private BufferedImage image = null;

	public Player(float x, float y) {
		super(x, y, 30, 30);
		this.image = Assets.get().playerSheet.getImage("player");
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, (int) (getX() - Camera.get().getX()),
				(int) (getY() - Camera.get().getY()), (int) getWidth(),
				(int) getHeight(), null);
	}

	@Override
	public void tick(double delta) {
		Camera.get().setPosition(this);
		updatePosition(delta);
	}

	public void updatePosition(double delta) {
		x += delta * getVelocity().getX();
		y += delta * getVelocity().getY();
	}

	public void inputPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			getVelocity().setX(-2f);
			break;
		case KeyEvent.VK_D:
			getVelocity().setX(2f);
			break;
		case KeyEvent.VK_W:
			getVelocity().setY(-2f);
			break;
		case KeyEvent.VK_S:
			getVelocity().setY(2f);
		}
	}

	public void inputReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			getVelocity().setX(0);
			break;
		case KeyEvent.VK_D:
			getVelocity().setX(0);
			break;
		case KeyEvent.VK_W:
			getVelocity().setY(0);
			break;
		case KeyEvent.VK_S:
			getVelocity().setY(0);
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
