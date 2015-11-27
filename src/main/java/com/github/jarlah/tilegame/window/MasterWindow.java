package com.github.jarlah.tilegame.window;

import javax.swing.JFrame;

public class MasterWindow {
    public MasterWindow() {
    	JFrame frame = new JFrame("Tile Game");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MasterCanvas masterCanvas = MasterCanvas.get();
        masterCanvas.startLoop();
        frame.add(masterCanvas.getCanvas());
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
