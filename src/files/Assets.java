package files;

import gfx.SpriteSheet;

public enum Assets {
    O;
    
    public SpriteSheet defaultSheet = new SpriteSheet("SpriteSheets/terrain.png");
    
    public SpriteSheet playerSheet = new SpriteSheet("Textures/Hero.png");
    
    private boolean loaded = false;
    public boolean isLoaded() { return loaded; }
    
    public void loadAll() {
        loadCroppedImages();
        loaded = true;
    }

    private void loadCroppedImages() {
        defaultSheet.loadCropped("grass", 16, 144, 16, 16);
        defaultSheet.loadCropped("tree", 240, 0, 16, 16);
        playerSheet.loadCropped("player", 64, 0, 16, 16);
    }
}
