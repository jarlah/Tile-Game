package files;

import graphics.Sprite;

public class Assets {
    public static Assets get() { 
        return Creator.object;
    }

    private static final class Creator {
        private static final Assets object = new Assets();
    }
    
    private final Sprite terrainSheet = new Sprite("SpriteSheets/terrain.png");
    private final Sprite playerSheet = new Sprite("SpriteSheets/player.png");
    private final Sprite enemySheet = new Sprite("Textures/ogre.png");
    
    private Assets() {}

	public Sprite getTerrainSheet() {
		return terrainSheet;
	}

	public Sprite getPlayerSheet() {
		return playerSheet;
	}

	public Sprite getEnemySheet() {
		return enemySheet;
	}
}
