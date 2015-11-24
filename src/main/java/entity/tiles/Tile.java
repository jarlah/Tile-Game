/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.tiles;

import java.awt.Graphics;

import files.Assets;

public class Tile {
    private final String name;
    
    public Tile(String name) {
    	this.name = name;
    }

    public void render(Graphics g, int x, int y, int size) {
        g.drawImage(Assets.get().defaultSheet.getImage(name), x, y, size, size, null);
    }

    public void tick(double delta) {
        
    }
}
