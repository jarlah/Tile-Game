package files;

import gfx.SpriteSheet;

public class Assets {
    public static Assets get() { 
        return Creator.object;
    }

    private static class Creator {
        private static final Assets object = new Assets();
    }
    
    private Assets() {}
    
    public final SpriteSheet defaultSheet = new SpriteSheet("SpriteSheets/terrain.png");
    
    public final SpriteSheet playerSheet = new SpriteSheet("Textures/Hero.png");
    
    public void loadAll() {
        loadCroppedImages();
    }

    private void loadCroppedImages() {
        defaultSheet.loadCropped("grass", 16, 144, 16, 16);
        defaultSheet.loadCropped("tree", 240, 0, 16, 16);
        playerSheet.loadCropped("player", 64, 0, 16, 16);
    }
}
