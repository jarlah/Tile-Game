package game;

import java.awt.Graphics;
import level.Map;
import screen.Screen;

public class Game extends Screen {
    private final Map map = new Map();
    
    @Override
    public void render(Graphics g) {
        map.render(g);
    }

    @Override
    public void tick(double delta) {
        map.tick(delta);
    }
    
}
