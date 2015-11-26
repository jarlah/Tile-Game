package entity.tiles;

import java.awt.image.BufferedImage;

import animation.Animation;
import files.Assets;


public class Dungeon extends  Tile {
	private static final int TREE_FRAME_DELAY = 60;
	
	private final Animation animation = new Animation (
		new BufferedImage[] { 
			Assets.get().getTerrainSheet().loadSprite("grass", 12, 2)
		}, TREE_FRAME_DELAY
	);
	
	public Dungeon(TileInfo tileInfo) {
        super(tileInfo, TILE_SIZE);
        setAnimation(animation);
    }
}
