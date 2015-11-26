package entity.tiles;

import java.awt.image.BufferedImage;

import animation.Animation;
import files.Assets;

public class Grass extends Tile {
	private static final int TREE_FRAME_DELAY = 60;
	
	private final Animation animation = new Animation (
		new BufferedImage[] { 
			Assets.get().getTerrainSheet().loadSprite("grass", 1, 9)
		}, TREE_FRAME_DELAY
	);
	
	public Grass(TileInfo tileInfo) {
        super(tileInfo, TILE_SIZE);
        setAnimation(animation);
    }
}
