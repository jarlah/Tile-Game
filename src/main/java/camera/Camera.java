package camera;

import entity.Entity;
import window.MasterCanvas;

public class Camera {
    public static Camera get() { 
        return Creator.object;
    }

    private static class Creator {
        private static final Camera object = new Camera();
    }

	private int x, y;

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
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
