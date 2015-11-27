package com.github.jarlah.tilegame.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.github.jarlah.tilegame.entity.tiles.Dungeon;
import com.github.jarlah.tilegame.entity.tiles.Enemy;
import com.github.jarlah.tilegame.entity.tiles.Grass;
import com.github.jarlah.tilegame.entity.tiles.MasterTile;
import com.github.jarlah.tilegame.entity.tiles.Tile;
import com.github.jarlah.tilegame.entity.tiles.TileInfo;
import com.github.jarlah.tilegame.entity.tiles.Tree;

public class Level {
	private MasterTile[][] tiles;
	private final int tileSize = 50;
	private final int minTilesX = 16 + 3;
	private final int minTilesY = 12 + 3;
	
	private int lengthX, lengthY;

	public Level(String link) {
		List<TileRow> rows = loadTileRows(link);
		lengthX = rows.get(0).tiles.size();
		if (lengthX < minTilesX) {
			throw new IllegalArgumentException("in level " + link + " -> length x " + lengthX + " is smaller than minimum length x " + minTilesX);
		}
		lengthY = rows.size();
		if (lengthY < minTilesY) {
			throw new IllegalArgumentException("in level " + link + " -> length y " + lengthY + " is smaller than minimum length y " + minTilesY);
		}
		tiles = new MasterTile[lengthX][lengthY];
		for (int x = 0; x < lengthX; x++) {
			for (int y = 0; y < lengthY; y++) {
				TileInfo tileInfo = new TileInfo(x * tileSize, y * tileSize, tileSize);
				Class<? extends Tile> tile = rows.get(y).tiles.get(x);
				Tile top = null;
				if (tile.isAssignableFrom(Tree.class)) {
					top = new Tree(tileInfo);
				}
				if (tile.isAssignableFrom(Enemy.class)) {
					top = new Enemy(tileInfo);
				}
				if (tile.isAssignableFrom(Dungeon.class)) {
					top = new Dungeon(tileInfo);
				}
				tiles[x][y] = new MasterTile(new Grass(tileInfo), top, tileInfo);
			}
		}
		
	}

	private List<TileRow> loadTileRows(String link) {
		List<TileRow> rows = new ArrayList<>();
		try {
			Reader r = new InputStreamReader(Level.class.getClassLoader().getResourceAsStream(link));
			BufferedReader br = new BufferedReader(r);
			while (br.ready()) {
				List<Class<? extends Tile>> tiles = new ArrayList<>();
				br.readLine().chars().mapToObj(Level::getTile).forEach((tile) -> tiles.add(tile));
				rows.add(new TileRow(tiles));
			}
			br.close();
		} catch (IOException ex) {
			throw new RuntimeException("Could not load level: " + link, ex);
		}
		return rows;
	}

	public static Class<? extends Tile> getTile(int letter) {
		char c = (char) letter;
		switch (c) {
		case 'C':
			return Dungeon.class;
		case 'T':
			return Tree.class;
		case 'E':
			return Enemy.class;
		default:
			return Grass.class;
		}
	}

	class TileRow { 
		List<Class<? extends Tile>> tiles; 
		TileRow(List<Class<? extends Tile>> tiles) {
			this.tiles = tiles;
		}
	}

	public MasterTile[][] getTiles() {
		return tiles;
	}

	public int getSize() {
		return tileSize;
	}

	public int getLengthX() {
		return lengthX;
	}

	public int getLengthY() {
		return lengthY;
	}
}
