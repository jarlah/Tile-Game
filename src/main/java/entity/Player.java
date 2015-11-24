package entity;

import java.awt.event.KeyEvent;

import files.Assets;

public class Player extends GameActor {
	public Player(float x, float y) {
		super(Assets.get().getPlayerSheet().getTexture("player"), x, y, 30, 30);
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
}
