/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.tiles;

import files.Assets;
import java.awt.Graphics;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Tile {
    private String name;

    public void render(Graphics g, int x, int y, int size) {
        g.drawImage(Assets.O.defaultSheet.getImage(name), x, y, size, size, null);
    }

    public void tick(double delta) {
        
    }
}
