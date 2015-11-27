package com.github.jarlah.tilegame.entity.tiles;

import java.awt.image.BufferedImage;

import com.github.jarlah.tilegame.animation.Animation;
import com.github.jarlah.tilegame.files.Assets;

public class Enemy extends Tile {
	private final Animation standingAn = new StandingAnimation(FRAME_DELAY);
	
	class StandingAnimation extends Animation{
		public StandingAnimation(int frameDelay) {
			super(new BufferedImage[]{
				Assets.get().getEnemySheet().loadSprite("enemyDownStill", 0, 0)
			}, frameDelay);
		} 
	}
	
	// This is the actual animation
	private final Animation animation = standingAn;

	public Enemy(TileInfo tileInfo) {
		super(tileInfo, TILE_SIZE);
		setAnimation(animation);
	}

	public Animation getStandingAn() {
		return standingAn;
	}

	public Animation getAnimation() {
		return animation;
	}
}
