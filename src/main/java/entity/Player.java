package entity;

import files.Assets;
import gfx.Animation;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends GameActor {
	private final int frameDelay = 10;
	
	private Animation walkLeftAn = new LeftAnimation(frameDelay);
	private Animation walkRightAn = new RightAnimation(frameDelay);
	private Animation standingAn = new StandingAnimation(frameDelay);
	private Animation walkUpAn = new UpAnimation(frameDelay);
	private Animation walkDownAn = new DownAnimation(frameDelay);
	
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
		animation = standingAn;
		setAnimation(animation);
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
			resetAnimation();
			break;
		case KeyEvent.VK_D:
			getVelocity().setX(0);
			resetAnimation();
			break;
		case KeyEvent.VK_W:
			getVelocity().setY(0);
			resetAnimation();
			break;
		case KeyEvent.VK_S:
			getVelocity().setY(0);
			resetAnimation();
		}
	}
}
