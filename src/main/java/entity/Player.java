package entity;

import java.awt.Graphics;
import camera.Camera;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import maths.Vector2f;

public class Player extends Entity{

    private final BufferedImage image;
    
    public Player(BufferedImage image, float x, float y, float width, float height, Vector2f velocity) {
        super(x, y, width, height, velocity);
        this.image = image;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)(getX()+Camera.C.getX()), (int)(getY()+Camera.C.getY()), (int)getWidth(), (int)getHeight(), null);
    }

    @Override
    public void tick(double delta) { 
        camera.Camera.C.setPosition(this);
        updatePosition(delta);
    }
    
    public void updatePosition(double delta) {
        x += delta * getVelocity().getX();
        y += delta * getVelocity().getY();
    }
    
    public void inputPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                getVelocity().setX(2f);
                break;
            case KeyEvent.VK_RIGHT:
                getVelocity().setX(-2f);
                break;
            case KeyEvent.VK_UP:
                getVelocity().setY(2f);
                break;
            case KeyEvent.VK_DOWN:
                getVelocity().setY(-2f);
        }
    }

    public void inputReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                getVelocity().setX(0);
                break;
            case KeyEvent.VK_RIGHT:
                getVelocity().setX(0);
                break;
            case KeyEvent.VK_UP:
                getVelocity().setY(0);
                break;
            case KeyEvent.VK_DOWN:
                getVelocity().setY(0);
        }
    }
}
