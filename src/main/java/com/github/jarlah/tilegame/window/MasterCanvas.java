package com.github.jarlah.tilegame.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.jarlah.tilegame.entity.EntityHandler;
import com.github.jarlah.tilegame.entity.Player;
import com.github.jarlah.tilegame.game.Plains;
import com.github.jarlah.tilegame.input.MasterKeyListener;
import com.github.jarlah.tilegame.input.MasterMouseListener;
import com.github.jarlah.tilegame.screen.ScreenHandler;

@SuppressWarnings("serial")
public class MasterCanvas extends Canvas implements Runnable {
    public static MasterCanvas get() { 
        return Creator.object;
    }

    private static class Creator {
        private static final MasterCanvas object = new MasterCanvas();
    }

    public static final int WIDTH = 800, HEIGHT = 600;

    private final Dimension defaultSize;

    private MasterCanvas() {
    	Player player = new Player(WIDTH/2-(40/2), HEIGHT/2-(40/2));
        ScreenHandler.get().setActiveScreen(Plains.get());
        ScreenHandler.get().setPlayer(player);
        EntityHandler.get().addEntity(player);
        defaultSize = new Dimension(WIDTH, HEIGHT);
        this.setMinimumSize(defaultSize);
        this.setMaximumSize(defaultSize);
        this.setPreferredSize(defaultSize);
        this.addKeyListener(new MasterKeyListener());
        MasterMouseListener mouseListener = new MasterMouseListener();
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.addMouseWheelListener(mouseListener);
        this.setFocusable(true);
    }
    
    public void addNotify() {
    	super.addNotify();
    	this.start();
    }

    public void stopLoop() {
        this.stop();
    }

    private void tick(double delta) {
        ScreenHandler.get().tick(delta);
        EntityHandler.get().tick(delta);
    }

    private void render(double delta) {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
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

    private final Thread gameThread = new Thread(this);
    private boolean running = false;
    private static final int FPSCAP = 120;

    @Override
    public void run() {
    	System.out.println("Starting ...");
        long mainTimer = System.nanoTime();
        double nsPerTick = 1000000000D / FPSCAP;
        long fpsTimer = System.currentTimeMillis();
        double delta = 0;

        while (running) {
            long timer = System.nanoTime();
            delta += (timer - mainTimer) / nsPerTick;
            mainTimer = timer;

            while (delta > 1) {
                tick(delta);
                render(delta);
                delta -= 1;
            }

            if ((System.currentTimeMillis() - fpsTimer) >= 1000) {
                fpsTimer += 1000;
            }
        }
        
        System.out.println("Stopping ...");
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
