package com.github.jarlah.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.github.jarlah.tilegame.screen.ScreenHandler;

public class MasterKeyListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		ScreenHandler.get().getPlayer().inputPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		ScreenHandler.get().getPlayer().inputReleased(e);
	}
}
