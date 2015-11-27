package com.github.jarlah.tilegame.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

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
	
	final double GAME_HERTZ = 30.0;
	final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
	final double TARGET_FPS = 120;
	final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
	final int MAX_UPDATES_BEFORE_REDNER = 5;

	private volatile boolean running = false;
	private volatile boolean paused = false;

	private int fps = 120;
	private int frameCount = 0;

	private final Thread gameThread = new Thread(this);

	private MasterCanvas() {
		Player player = new Player(WIDTH / 2 - (40 / 2), HEIGHT / 2 - (40 / 2));
		ScreenHandler.get().setActiveScreen(Plains.get());
		ScreenHandler.get().setPlayer(player);
		EntityHandler.get().addEntity(player);
		Dimension defaultSize = new Dimension(WIDTH, HEIGHT);
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

	private void update() {
		ScreenHandler.get().tick();
		EntityHandler.get().tick();
	}

	public void render(Graphics g) {
		g.setColor(new Color(158, 172, 255));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		ScreenHandler.get().render(g);
		EntityHandler.get().render(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 65, 20);
		g.setColor(Color.BLACK);
		g.drawString("FPS: " + fps, 5, 15);
		frameCount++;
	}

	@Override
	public void run() {
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = System.nanoTime();
		
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);

		while (running) {
			double now = System.nanoTime();
			int updateCount = 0;

			if (!paused) {
				while (now - lastUpdateTime > TIME_BETWEEN_UPDATES
						&& updateCount < MAX_UPDATES_BEFORE_REDNER) {
					update();
					lastUpdateTime += TIME_BETWEEN_UPDATES;
					updateCount++;
				}

				if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
					lastUpdateTime = now - TIME_BETWEEN_UPDATES;
				}

				drawGame();

				lastRenderTime = now;

				int thisSecond = (int) (lastUpdateTime / 1000000000);
				if (thisSecond > lastSecondTime) {
					System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
					fps = frameCount;
					frameCount = 0;
					lastSecondTime = thisSecond;
				}

				while ((now - lastRenderTime) < TARGET_TIME_BETWEEN_RENDERS
						&& (now - lastUpdateTime) < TIME_BETWEEN_UPDATES) {
					Thread.yield();
					try {Thread.sleep(1);} catch (Exception e) {}
					now = System.nanoTime();
				}
			}
		}
	}

	private void drawGame() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		render(g);
		g.dispose();
		bs.show();
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
			try {gameThread.join();} catch (InterruptedException ex) {}
		}
	}
}
