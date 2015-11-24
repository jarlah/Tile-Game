package entity;

import java.awt.event.KeyEvent;

import files.Assets;

public class Player extends GameActor {
	public Player(float x, float y) {
		super(x, y, 30, 30);
		setTexture(Assets.get().getPlayerSheet().getTexture("playerDownStill"));
	}

	public void inputPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			getVelocity().setX(-2f);
			setTexture(Assets.get().getPlayerSheet().getTexture("playerLeftWalk"));
			break;
		case KeyEvent.VK_D:
			getVelocity().setX(2f);
			setTexture(Assets.get().getPlayerSheet().getTexture("playerRightWalk"));
			break;
		case KeyEvent.VK_W:
			getVelocity().setY(-2f);
			setTexture(Assets.get().getPlayerSheet().getTexture("playerUpWalk"));
			break;
		case KeyEvent.VK_S:
			getVelocity().setY(2f);
			setTexture(Assets.get().getPlayerSheet().getTexture("playerDownWalk"));
		}
	}

	public void inputReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			getVelocity().setX(0);
			setTexture(Assets.get().getPlayerSheet().getTexture("playerDownStill"));
			break;
		case KeyEvent.VK_D:
			getVelocity().setX(0);
			setTexture(Assets.get().getPlayerSheet().getTexture("playerDownStill"));
			break;
		case KeyEvent.VK_W:
			getVelocity().setY(0);
			setTexture(Assets.get().getPlayerSheet().getTexture("playerUpStill"));
			break;
		case KeyEvent.VK_S:
			getVelocity().setY(0);
			setTexture(Assets.get().getPlayerSheet().getTexture("playerDownStill"));
		}
	}
}
