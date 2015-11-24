package input;

import game.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HomeKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Game.G().getPlayer().inputPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Game.G().getPlayer().inputReleased(e);
    }
    
}
