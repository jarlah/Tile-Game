package entity.tiles;

import java.awt.image.BufferedImage;

import animation.Animation;
import files.Assets;

public class Tree extends Tile {
	private static final int TREE_FRAME_DELAY = 60;
	
	private final Animation animation = new Animation (
		new BufferedImage[] { 
			Assets.get().getTerrainSheet().loadSprite("tree", 15, 0)
		}, TREE_FRAME_DELAY
	);

	public Tree(TileInfo tileInfo) {
		super(tileInfo, TILE_SIZE);
		setAnimation(animation);
	}
}
