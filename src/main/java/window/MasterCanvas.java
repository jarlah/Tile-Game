package window;

import input.MasterKeyListener;
import input.MasterMouseListener;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

import screen.ScreenHandler;
import entity.EntityHandler;
import entity.Player;
import game.Plains;

public class MasterCanvas {
    public static MasterCanvas get() { 
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
    private double loopDelta = 0d;

    private MasterCanvas() {
    	Player player = new Player(WIDTH/2-(40/2), HEIGHT/2-(40/2));
        ScreenHandler.get().setActiveScreen(Plains.get());
        ScreenHandler.get().setPlayer(player);
        EntityHandler.get().addEntity(player);
        defaultSize = new Dimension(WIDTH, HEIGHT);
        canvas = new Canvas();
        canvas.setMinimumSize(defaultSize);
        canvas.setMaximumSize(defaultSize);
        canvas.setPreferredSize(defaultSize);
        canvas.addKeyListener(new MasterKeyListener());
        MasterMouseListener mouseListener = new MasterMouseListener();
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

    private void tick(double delta) {
        ScreenHandler.get().tick(delta);
        EntityHandler.get().tick(delta);
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
        EntityHandler.get().render(g);
        g.dispose();
        bs.show();
    }

    private class GameLoop implements Runnable {

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

	public Canvas getCanvas() {
		return canvas;
	}

	public GameLoop getLoop() {
		return loop;
	}

	public Dimension getDefaultSize() {
		return defaultSize;
	}

	public int getLoopTicks() {
		return loopTicks;
	}

	public int getLoopFrames() {
		return loopFrames;
	}

	public double getLoopDelta() {
		return loopDelta;
	}
}
