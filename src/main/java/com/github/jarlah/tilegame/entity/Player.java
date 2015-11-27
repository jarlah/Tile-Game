package com.github.jarlah.tilegame.entity;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.github.jarlah.tilegame.animation.Animation;
import com.github.jarlah.tilegame.entity.tiles.Dungeon;
import com.github.jarlah.tilegame.entity.tiles.Enemy;
import com.github.jarlah.tilegame.entity.tiles.Tile;
import com.github.jarlah.tilegame.files.Assets;
import com.github.jarlah.tilegame.game.Castle;
import com.github.jarlah.tilegame.maths.Vector2f;
import com.github.jarlah.tilegame.screen.ScreenHandler;

public class Player extends GameActor {
	private static final int FRAME_DELAY = 4;
	private boolean goingRight = false, goingLeft = false, goingUp = false,
			goingDown = false;
	private Animation walkLeftAn = new LeftAnimation(FRAME_DELAY);
	private Animation walkRightAn = new RightAnimation(FRAME_DELAY);
	private Animation standingAn = new StandingAnimation(FRAME_DELAY);
	private Animation walkUpAn = new UpAnimation(FRAME_DELAY);
	private Animation walkDownAn = new DownAnimation(FRAME_DELAY);

	class UpAnimation extends Animation {
		public UpAnimation(int frameDelay) {
			super(new BufferedImage[] {
					Assets.get().getPlayerSheet()
							.loadSprite("playerUpWalk1", 0, 0),
					Assets.get().getPlayerSheet()
							.loadSprite("playerUpWalk2", 1, 0),
					Assets.get().getPlayerSheet()
							.loadSprite("playerUpWalk3", 2, 0),
					Assets.get().getPlayerSheet()
							.loadSprite("playerUpWalk4", 3, 0) }, frameDelay);
		}
	}

	class DownAnimation extends Animation {
		public DownAnimation(int frameDelay) {
			super(new BufferedImage[] {
					Assets.get().getPlayerSheet()
							.loadSprite("playerDownWalk1", 4, 0),
					Assets.get().getPlayerSheet()
							.loadSprite("playerDownWalk1", 5, 0),
					Assets.get().getPlayerSheet()
							.loadSprite("playerDownWalk2", 6, 0),
					Assets.get().getPlayerSheet()
							.loadSprite("playerDownWalk2", 7, 0) }, frameDelay);
		}
	}

	class LeftAnimation extends Animation {
		public LeftAnimation(int frameDelay) {
			super(new BufferedImage[] {
					Assets.get().getPlayerSheet()
							.loadSprite("playerLeftWalk1", 0, 1),
					Assets.get().getPlayerSheet()
							.loadSprite("playerLeftWalk2", 1, 1),
					Assets.get().getPlayerSheet()
							.loadSprite("playerLeftWalk3", 2, 1),
					Assets.get().getPlayerSheet()
							.loadSprite("playerLeftWalk4", 3, 1) }, frameDelay);
		}
	}

	class RightAnimation extends Animation {
		public RightAnimation(int frameDelay) {
			super(new BufferedImage[] {
					Assets.get().getPlayerSheet()
							.loadSprite("playerRightWalk1", 7, 1),
					Assets.get().getPlayerSheet()
							.loadSprite("playerRightWalk2", 6, 1),
					Assets.get().getPlayerSheet()
							.loadSprite("playerRightWalk3", 5, 1),
					Assets.get().getPlayerSheet()
							.loadSprite("playerRightWalk4", 4, 1) }, frameDelay);
		}
	}

	class StandingAnimation extends Animation {
		public StandingAnimation(int frameDelay) {
			super(new BufferedImage[] { Assets.get().getPlayerSheet()
					.loadSprite("playerDownStill", 4, 0) }, frameDelay);
		}
	}

	// This is the actual animation
	private Animation animation;

	public Player(float x, float y) {
		this(x, y, new Vector2f(0, 0));
	}

	public Player(float x, float y, Vector2f velocity) {
		super(x, y, 40, 40, velocity);
		setAnimation(animation = standingAn);
		animation.start();
	}

	private void startLeftAnimation() {
		setAnimation(animation = walkLeftAn);
		animation.start();
	}

	private void startRightAnimation() {
		setAnimation(animation = walkRightAn);
		animation.start();
	}

	private void startUpAnimation() {
		setAnimation(animation = walkUpAn);
		animation.start();
	}

	private void startDownAnimation() {
		setAnimation(animation = walkDownAn);
		animation.start();
	}

	public void inputPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			getVelocity().setX(-3f);
			goingLeft = true;
			break;
		case KeyEvent.VK_D:
			getVelocity().setX(3f);
			goingRight = true;
			break;
		case KeyEvent.VK_W:
			getVelocity().setY(-3f);
			goingUp = true;
			break;
		case KeyEvent.VK_S:
			getVelocity().setY(3f);
			goingDown = true;
		}
		startAnimation();
	}

	public void inputReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			getVelocity().setX(0);
			goingLeft = false;
			break;
		case KeyEvent.VK_D:
			getVelocity().setX(0);
			goingRight = false;
			break;
		case KeyEvent.VK_W:
			getVelocity().setY(0);
			goingUp = false;
			break;
		case KeyEvent.VK_S:
			getVelocity().setY(0);
			goingDown = false;
		}
		startAnimation();
	}

	private void stopAnimation() {
		animation.stop();
		animation.reset();
	}

	private void startAnimation() {
		boolean started = false;
		if (goingRight) {
			startRightAnimation();
			started = true;
		} else if (goingLeft) {
			startLeftAnimation();
			started = true;
		}
		if (goingUp) {
			startUpAnimation();
			started = true;
		} else if (goingDown) {
			startDownAnimation();
			started = true;
		}
		if (!started) {
			stopAnimation();
		}
	}

	@Override
	public void handleCollision(Tile collisionX, Tile collisionY) {
		if (collisionX instanceof Dungeon || collisionY instanceof Dungeon) {
			System.out.println("Entering dungeon");
			ScreenHandler.get().setActiveScreen(Castle.get());
		}
		if (collisionX instanceof Enemy || collisionY instanceof Enemy) {
			System.out.println("Whoaaa!! an ememy!!");
		}
	}

	@Override
	public void handleBorderCrossing() {
		getVelocity().setX(0);
		getVelocity().setY(0);
		if (getOldX() != getX()) {
			setX(getOldX());
		}
		if (getOldY() != getY()) {
			setY(getOldY());
		}
		stopAnimation();
	}
}
