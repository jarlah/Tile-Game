package files;

import gfx.Sprite;

public class Assets {
    public static Assets get() { 
        return Creator.object;
    }

    private static class Creator {
        private static final Assets object = new Assets();
    }
    
    private final Sprite defaultSheet = new Sprite("SpriteSheets/terrain.png");
    private final Sprite playerSheet = new Sprite("Textures/Hero.png");
    
    private Assets() {}

//        defaultSheet.loadSprite("grass", 16, 144);
//        defaultSheet.loadSprite("tree", 240, 0);
//        playerSheet.loadSprite("playerLeftWalk1", 48, 16);
//        playerSheet.loadSprite("playerLeftWalk2", 16, 16);
//        playerSheet.loadSprite("playerRightWalk1", 64, 16);
//        playerSheet.loadSprite("playerRightWalk2", 96, 16);
//        playerSheet.loadSprite("playerDownStill", 64, 0);
//        playerSheet.loadSprite("playerDownWalk1", 80, 0);
//        playerSheet.loadSprite("playerDownWalk2", 112, 0);
//        playerSheet.loadSprite("playerUpStill", 32, 0);
//        playerSheet.loadSprite("playerUpWalk1", 48, 0);
//        playerSheet.loadSprite("playerUpWalk2", 16, 0);

	public Sprite getDefaultSheet() {
		return defaultSheet;
	}

	public Sprite getPlayerSheet() {
		return playerSheet;
	}
}
