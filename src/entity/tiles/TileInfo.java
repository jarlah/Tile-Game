
package entity.tiles;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TileInfo {
    private String name;
    private int x, y, size;
}
