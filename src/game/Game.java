package game;

import java.awt.Graphics;
import level.Level;
import screen.Screen;

public class Game extends Screen {
    private final Level level = new Level();
    
    @Override
    public void render(Graphics g) {
        level.render(g);
    }

    @Override
    public void tick(double delta) {
        level.tick(delta);
    }
    
}
