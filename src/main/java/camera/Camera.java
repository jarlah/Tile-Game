package camera;

import entity.Entity;
import java.awt.event.KeyEvent;
import window.MasterCanvas;

public enum Camera {
    C;

    private int x, y, speedX, speedY;

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void inputPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                speedX = -5;
                break;
            case KeyEvent.VK_RIGHT:
                speedX = 5;
                break;
            case KeyEvent.VK_UP:
                speedY = -5;
                break;
            case KeyEvent.VK_DOWN:
                speedY = 5;
        }
    }

    public void inputReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                speedX = 0;
                break;
            case KeyEvent.VK_RIGHT:
                speedX = 0;
                break;
            case KeyEvent.VK_UP:
                speedY = 0;
                break;
            case KeyEvent.VK_DOWN:
                speedY = 0;
        }
    }

    public void updatePosition() {
        y += MasterCanvas.Singleton.getDelta() * speedY;
        x += MasterCanvas.Singleton.getDelta() * speedX;
    }
    
    public void setPosition(Entity e) {
        x = (int) e.getX() - (MasterCanvas.WIDTH/2);
        y = (int) e.getY() - (MasterCanvas.HEIGHT/2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
