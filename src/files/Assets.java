package files;

import gfx.SpriteSheet;

public enum Assets {
    Singleton;
    
    public SpriteSheet defaultSheet = new SpriteSheet("SpriteSheets/terrain.png");
    private boolean loaded = false;
    public boolean isLoaded() { return loaded; }
    
    public void loadAll() {
        loadCroppedImages();
        loaded = true;
    }

    private void loadCroppedImages() {
        defaultSheet.loadCropped("grass", 16, 144, 16, 16);
    }
}
