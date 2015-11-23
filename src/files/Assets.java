package files;

import gfx.SpriteSheet;

public final class Assets {
    public static SpriteSheet defaultSheet = new SpriteSheet("SpriteSheets/terrain.png");
    
    private static boolean loaded = false;
    
    public static boolean isLoaded() { return loaded; }
    
    public static void loadAll() {
        loadCroppedImages();
        loaded = true;
    }

    private static void loadCroppedImages() {
        defaultSheet.loadCropped("test", 16, 144, 16, 16);
    }
}
