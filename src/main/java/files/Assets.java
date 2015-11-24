package files;

import gfx.SpriteSheet;

public class Assets {
    public static Assets get() { 
        return Creator.object;
    }

    private static class Creator {
        private static final Assets object = new Assets();
    }
    
    private final SpriteSheet defaultSheet;
    private final SpriteSheet playerSheet;
    
    private Assets() {
    	defaultSheet = new SpriteSheet("SpriteSheets/terrain.png");
        playerSheet = new SpriteSheet("Textures/Hero.png");
    }
    
    public void loadAll() {
        loadCroppedImages();
    }

    private void loadCroppedImages() {
        defaultSheet.loadCropped("grass", 16, 144, 16, 16);
        defaultSheet.loadCropped("tree", 240, 0, 16, 16);
        playerSheet.loadCropped("playerLeftWalk1", 48, 16, 16, 16);
        playerSheet.loadCropped("playerLeftWalk2", 16, 16, 16, 16);
        playerSheet.loadCropped("playerRightWalk1", 64, 16, 16, 16);
        playerSheet.loadCropped("playerRightWalk2", 96, 16, 16, 16);
        playerSheet.loadCropped("playerDownStill", 64, 0, 16, 16);
        playerSheet.loadCropped("playerDownWalk1", 80, 0, 16, 16);
        playerSheet.loadCropped("playerDownWalk2", 112, 0, 16, 16);
        playerSheet.loadCropped("playerUpStill", 32, 0, 16, 16);
        playerSheet.loadCropped("playerUpWalk1", 48, 0, 16, 16);
        playerSheet.loadCropped("playerUpWalk2", 16, 0, 16, 16);
    }

	public SpriteSheet getDefaultSheet() {
		return defaultSheet;
	}

	public SpriteSheet getPlayerSheet() {
		return playerSheet;
	}
}
