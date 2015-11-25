package screen;

import java.awt.Graphics;

import lombok.Getter;
import lombok.Setter;
import camera.Camera;
import entity.Player;
import entity.tiles.MasterTile;

@Getter
@Setter
public class ScreenHandler extends Screen {
    public static ScreenHandler get() { 
        return Creator.object;
    }

    private static class Creator {
        private static final ScreenHandler object = new ScreenHandler();
    }

    private ScreenHandler() {}
    
    private Screen activeScreen;
	private Player player;

    @Override
    public void render(Graphics g) {
        if (activeScreen != null) {
            activeScreen.render(g);
        }
    }

    @Override
    public void tick(double delta) {
        if (activeScreen != null) {
        	Camera.get().setPosition(player);
            activeScreen.tick(delta);
        }
    }

	@Override
	public MasterTile getTile(int x, int y) {
		if (activeScreen != null) {
			return activeScreen.getTile(x, y);
		}
		return null;
	}
}
