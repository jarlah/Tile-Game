package game;

import java.awt.Graphics;

import level.Level;
import screen.Screen;
import window.MasterCanvas;
import entity.EntityHandler;
import entity.Player;

public class Game extends Screen {
    public static Game G() { 
        return Creator.object;
    }

    private static class Creator {
        private static final Game object = new Game();
    }

    private final Level level;
    private final Player player;

    private Game() {
        this.level = new Level();
        this.player = new Player(MasterCanvas.WIDTH/2-(30/2), MasterCanvas.HEIGHT/2-(30/2));
    	EntityHandler.get().addEntity(player);
    }
    
    @Override
    public void render(Graphics g) {
        level.render(g);
        EntityHandler.get().render(g);
    }

    @Override
    public void tick(double delta) {
        level.tick(delta);
        EntityHandler.get().tick(delta);
    }

	public Level getLevel() {
		return level;
	}

	public Player getPlayer() {
		return player;
	}
    
}
