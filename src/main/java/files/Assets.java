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
        playerSheet.loadCropped("player", 64, 0, 16, 16);
    }

	public SpriteSheet getDefaultSheet() {
		return defaultSheet;
	}

	public SpriteSheet getPlayerSheet() {
		return playerSheet;
	}
}
