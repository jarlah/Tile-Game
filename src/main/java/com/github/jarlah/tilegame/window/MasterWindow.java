package com.github.jarlah.tilegame.window;

import javax.swing.JFrame;

public class MasterWindow {
    private static final String TITLE = "Tile Game";
    private final JFrame frame;
    private final MasterCanvas masterCanvas;

    public MasterWindow() {
    	masterCanvas = MasterCanvas.get();
        frame = new JFrame(TITLE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(masterCanvas.getCanvas());
        frame.pack();
        frame.setLocationRelativeTo(null);
        masterCanvas.startLoop();
    }
}
