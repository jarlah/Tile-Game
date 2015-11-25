
package entity.tiles;

import lombok.Getter;


@Getter
public class TileInfo {
    private final int x, y, size;
    
    public TileInfo(int x, int y, int size) {
    	this.x = x;
    	this.y = y;
    	this.size = size;
    }
}
