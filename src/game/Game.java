package game;

import entity.EntityHandler;
import entity.Player;
import files.Assets;
import java.awt.Graphics;
import level.Level;
import screen.Screen;
import window.MasterCanvas;
import camera.Camera;
import gfx.SpriteSheet;

public class Game extends Screen {
    private final Level level = new Level();
    private final EntityHandler entityHandler = EntityHandler.Singleton;
    private final Camera camera = Camera.Singleton;
    private final SpriteSheet playerSheet = Assets.Singleton.playerSheet;
    
    public Game() {
        entityHandler.addEntity(new Player(playerSheet.getImage("player"), MasterCanvas.WIDTH/2-(50/2), MasterCanvas.HEIGHT/2-(50/2), 50, 50));
    }
    
    @Override
    public void render(Graphics g) {
        level.render(g);
        entityHandler.render(g);
    }

    @Override
    public void tick(double delta) {
        camera.updatePosition();
        level.tick(delta);
        entityHandler.tick(delta);
    }
    
}
