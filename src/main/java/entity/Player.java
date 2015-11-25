package entity;

import files.Assets;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import animation.Animation;

public class Player extends GameActor {
	private static final int FRAME_DELAY = 10;
	
	private Animation walkLeftAn = new LeftAnimation(FRAME_DELAY);
	private Animation walkRightAn = new RightAnimation(FRAME_DELAY);
	private Animation standingAn = new StandingAnimation(FRAME_DELAY);
	private Animation walkUpAn = new UpAnimation(FRAME_DELAY);
	private Animation walkDownAn = new DownAnimation(FRAME_DELAY);
	
	class UpAnimation extends Animation{
		public UpAnimation(int frameDelay) {
			super(new BufferedImage[]{
				Assets.get().getPlayerSheet().loadSprite("playerUpWalk1", 3, 0), 
				Assets.get().getPlayerSheet().loadSprite("playerUpWalk2", 1, 0)
			}, frameDelay);
		} 
	}
	
	class DownAnimation extends Animation{
		public DownAnimation(int frameDelay) {
			super(new BufferedImage[]{
				Assets.get().getPlayerSheet().loadSprite("playerDownWalk1", 5, 0), 
				Assets.get().getPlayerSheet().loadSprite("playerDownWalk2", 7, 0)
			}, frameDelay);
		} 
	}
	
	class LeftAnimation extends Animation{
		public LeftAnimation(int frameDelay) {
			super(new BufferedImage[]{
				Assets.get().getPlayerSheet().loadSprite("playerLeftWalk1", 3, 1), 
				Assets.get().getPlayerSheet().loadSprite("playerLeftWalk2", 1, 1)
			}, frameDelay);
		} 
	}
	
	class RightAnimation extends Animation{
		public RightAnimation(int frameDelay) {
			super(new BufferedImage[]{
				Assets.get().getPlayerSheet().loadSprite("playerRightWalk1", 4, 1), 
				Assets.get().getPlayerSheet().loadSprite("playerRightWalk2", 6, 1)
			}, frameDelay);
		} 
	}
	
	class StandingAnimation extends Animation{
		public StandingAnimation(int frameDelay) {
			super(new BufferedImage[]{
				Assets.get().getPlayerSheet().loadSprite("playerDownStill", 4, 0)
			}, frameDelay);
		} 
	}
	
	// This is the actual animation
	private Animation animation;
	
	public Player(float x, float y) {
		super(x, y, 30, 30);
		initialAnimation();
	}
	
	private void initialAnimation() {
		animation = standingAn;
		animation.start();
		setAnimation(animation);
	}
	
	private void startLeftAnimation() {
		animation = walkLeftAn;
		animation.start();
		setAnimation(animation);
	}
	
	private void startRightAnimation() {
		animation = walkRightAn;
		animation.start();
		setAnimation(animation);
	}
	
	private void startUpAnimation() {
		animation = walkUpAn;
		animation.start();
		setAnimation(animation);
	}
	
	private void startDownAnimation() {
		animation = walkDownAn;
		animation.start();
		setAnimation(animation);
	}
	
	private void resetAnimation() {
		animation.stop();
		animation.reset();
	}
   
	public void inputPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			getVelocity().setX(-2f);
			startLeftAnimation();
			break;
		case KeyEvent.VK_D:
			getVelocity().setX(2f);
			startRightAnimation();
			break;
		case KeyEvent.VK_W:
			getVelocity().setY(-2f);
			startUpAnimation();
			break;
		case KeyEvent.VK_S:
			getVelocity().setY(2f);
			startDownAnimation();
		}
	}

	public void inputReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			getVelocity().setX(0);
			if (animation instanceof LeftAnimation) {
				resetAnimation();
			}
			break;
		case KeyEvent.VK_D:
			getVelocity().setX(0);
			if (animation instanceof RightAnimation) {
				resetAnimation();
			}
			break;
		case KeyEvent.VK_W:
			getVelocity().setY(0);
			if (animation instanceof UpAnimation) {
				resetAnimation();
			}
			break;
		case KeyEvent.VK_S:
			getVelocity().setY(0);
			if (animation instanceof DownAnimation) {
				resetAnimation();
			}
		}
	}
}
