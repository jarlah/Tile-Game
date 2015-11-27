package com.github.jarlah.tilegame.files;

import com.github.jarlah.tilegame.entity.tiles.Tile;

public abstract class TileCreator {
	public abstract Class<? extends Tile> createTile(int letter);
}