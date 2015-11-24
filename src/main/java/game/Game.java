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
import maths.Vector2f;

public class Game extends Screen {
    private final Level level = new Level();
    private final EntityHandler entityHandler = EntityHandler.Singleton;
    private final Camera camera = Camera.C;
    private final SpriteSheet playerSheet = Assets.A.playerSheet;
    
    public Game() {
        entityHandler.addEntity(new Player(playerSheet.getImage("player"), MasterCanvas.WIDTH/2-(30/2), MasterCanvas.HEIGHT/2-(30/2), 30, 30, new Vector2f()));
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
