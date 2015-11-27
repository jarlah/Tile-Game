package com.github.jarlah.tilegame.camera;

import com.github.jarlah.tilegame.entity.Entity;
import com.github.jarlah.tilegame.window.MasterCanvas;

public class Camera {
	public static Camera get() {
		return Creator.object;
	}

	private static class Creator {
		private static final Camera object = new Camera();
	}

	private int x, y;

	private Camera() {
	}

	public void setPosition(Entity e) {
		x = (int) e.getX() - MasterCanvas.WIDTH / 2;
		y = (int) e.getY() - MasterCanvas.HEIGHT / 2;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
