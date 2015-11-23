/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.tiles;

import entity.Entity;
import files.Assets;
import java.awt.Graphics;
import camera.Camera;
/**
 *
 * @author jarlandre
 */
public class Tile extends Entity {
    private final String name;
    
    public Tile(String name, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.name = name;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.Singleton.defaultSheet.getImage(name), x-Camera.Singleton.getX(), y-Camera.Singleton.getY(), width, height, null);
    }

    @Override
    public void tick(long delta) {
        // nothing atm
    }
    
}
