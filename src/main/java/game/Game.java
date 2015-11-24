package game;

import java.awt.Graphics;

import camera.Camera;
import level.Level;
import screen.Screen;
import window.MasterCanvas;
import entity.EntityHandler;
import entity.Player;

public class Game extends Screen {
    public static Game get() { 
        return Creator.object;
    }

    private static class Creator {
        private static final Game object = new Game();
    }

    private final Level level;
    private final Player player;

    private Game() {
        this.level = Level.get();
        this.player = new Player(MasterCanvas.WIDTH/2-(40/2), MasterCanvas.HEIGHT/2-(40/2));
    	EntityHandler.get().addEntity(player);
    }
    
    @Override
    public void render(Graphics g) {
        level.render(g);
        EntityHandler.get().render(g);
    }

    @Override
    public void tick(double delta) {
    	Camera.get().setPosition(player);
        level.tick(delta);
        EntityHandler.get().tick(delta);
    }

	public Player getPlayer() {
		return player;
	}
    
}
