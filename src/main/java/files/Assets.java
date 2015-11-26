package files;

import graphics.Sprite;
import lombok.Getter;

@Getter
public class Assets {
    public static Assets get() { 
        return Creator.object;
    }

    private static final class Creator {
        private static final Assets object = new Assets();
    }
    
    private final Sprite terrainSheet = new Sprite("SpriteSheets/terrain.png");
    private final Sprite playerSheet = new Sprite("SpriteSheets/player.png");
    
    private Assets() {}
}
