package entity;

import java.awt.Graphics;
import camera.Camera;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import window.MasterCanvas;

public class Player extends Entity{

    private final BufferedImage image;
    private int speedX, speedY;
    
    public Player(BufferedImage image, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.image = image;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, getX()+Camera.Singleton.getX(), getY()+Camera.Singleton.getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick(double delta) { 
        camera.Camera.Singleton.setPosition(this);
        updatePosition(delta);
    }
    
    public void updatePosition(double delta) {
        x += delta*speedX/3;
        y += delta*speedY/3;
    }
    
    public void inputPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                speedX = 4;
                break;
            case KeyEvent.VK_RIGHT:
                speedX = -3;
                break;
            case KeyEvent.VK_UP:
                speedY = 4;
                break;
            case KeyEvent.VK_DOWN:
                speedY = -3;
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
}
