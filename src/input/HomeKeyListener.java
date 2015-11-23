package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import camera.Camera;

public class HomeKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Camera.Singleton.inputPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Camera.Singleton.inputReleased(e);
    }
    
}
