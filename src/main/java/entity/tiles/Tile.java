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
	private int spriteX;
	private int spriteY;
    
    public Tile(String name, int x, int y) {
    	this.name = name;
    	this.spriteX = x;
    	this.spriteY = y;
    }

    public void render(Graphics g, int x, int y, int size) {
        g.drawImage(Assets.get().getDefaultSheet().loadSprite(name, spriteX, spriteY), x, y, size, size, null);
    }

    public void tick(double delta) {
        
    }
}
