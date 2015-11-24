package screen;

import java.awt.Graphics;

public class ScreenHandler extends Screen {
    public static ScreenHandler get() { 
        return Creator.object;
    }

    private static class Creator {
        private static final ScreenHandler object = new ScreenHandler();
    }

    private ScreenHandler() {}
    
    private Screen activeScreen = null;

    @Override
    public void render(Graphics g) {
        if (activeScreen != null) {
            activeScreen.render(g);
        }
    }

    @Override
    public void tick(double delta) {
        if (activeScreen != null) {
            activeScreen.tick(delta);
        }
    }
    
    public void setActiveScreen(Screen screen) {
        this.activeScreen = screen;
    }

}
