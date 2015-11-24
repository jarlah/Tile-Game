package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import camera.Camera;
import entity.EntityHandler;
import entity.Player;

public class HomeKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Camera.C.inputPressed(e);
        Player p = (Player)EntityHandler.Singleton.getEntities().get(0);
        p.inputPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Camera.C.inputReleased(e);
        Player p = (Player)EntityHandler.Singleton.getEntities().get(0);
        p.inputReleased(e);
    }
    
}
