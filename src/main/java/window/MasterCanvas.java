package window;

import input.HomeKeyListener;
import input.HomeMouseListener;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

import screen.ScreenHandler;
import files.Assets;
import game.Game;

public class MasterCanvas {
    public static MasterCanvas M() { 
        return Creator.object;
    }

    private static class Creator {
        private static final MasterCanvas object = new MasterCanvas();
    }

    public static final int WIDTH = 800, HEIGHT = 600;
    private final Canvas canvas;
    private final GameLoop loop;
    private final Dimension defaultSize;
    private int loopTicks = 0;
    private int loopFrames = 0;
    private double loopDelta;

    private MasterCanvas() {
        Assets.get().loadAll();
        ScreenHandler.get().setActiveScreen(Game.G());
        defaultSize = new Dimension(WIDTH, HEIGHT);
        canvas = new Canvas();
        canvas.setMinimumSize(defaultSize);
        canvas.setMaximumSize(defaultSize);
        canvas.setPreferredSize(defaultSize);
        HomeKeyListener keyListener = new HomeKeyListener();
        HomeMouseListener mouseListener = new HomeMouseListener();
        canvas.addKeyListener(keyListener);
        canvas.addMouseListener(mouseListener);
        canvas.addMouseMotionListener(mouseListener);
        canvas.addMouseWheelListener(mouseListener);
        canvas.setFocusable(true);
        loop = new GameLoop();
    }

    public void startLoop() {
        loop.start();
    }

    public void stopLoop() {
        loop.stop();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private void tick(double delta) {
        ScreenHandler.get().tick(delta);
    }

    private void render(double delta) {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(158, 172, 255));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        ScreenHandler.get().render(g);
        g.dispose();
        bs.show();
    }

    class GameLoop implements Runnable {

        private final Thread gameThread = new Thread(this);
        private boolean running = false;
        private static final int FPSCAP = 120;

        @Override
        public void run() {
            long mainTimer = System.nanoTime();
            double nsPerTick = 1000000000D / FPSCAP;
            long fpsTimer = System.currentTimeMillis();
            double delta = 0;
            int ticks = 0;
            int frames = 0;

            while (running) {
                long timer = System.nanoTime();
                delta += (timer - mainTimer) / nsPerTick;
                mainTimer = timer;

                while (delta > 1) {
                    ticks++;
                    loopDelta = delta;
                    tick(delta);
                    frames++;
                    render(delta);
                    delta -= 1;
                }

                if ((System.currentTimeMillis() - fpsTimer) >= 1000) {
                    fpsTimer += 1000;
                    loopTicks = ticks;
                    loopFrames = frames;
                    ticks = 0;
                    frames = 0;
                }
            }
        }

        public final synchronized void start() {
            if (!running) {
                running = true;
                gameThread.start();
            }
        }

        public final synchronized void stop() {
            if (running) {
                running = false;
                try {
                    gameThread.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MasterCanvas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int getTicks() {
        return loopTicks;
    }

    public int getFrames() {
        return loopFrames;
    }

    public double getDelta() {
        return loopDelta;
    }
}
