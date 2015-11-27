package com.github.jarlah.tilegame.animation;

import java.awt.image.BufferedImage;

public class Frame {

	private final BufferedImage frame;
	private final int duration;

	public Frame(BufferedImage frame, int duration) {
		this.frame = frame;
		this.duration = duration;
	}

	public BufferedImage getFrame() {
		return frame;
	}

	public int getDuration() {
		return duration;
	}
}
