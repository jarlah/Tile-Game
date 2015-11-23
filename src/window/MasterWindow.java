package window;

import javax.swing.JFrame;

public class MasterWindow {
    private static final String TITLE = "Mini Tennis";
    private final JFrame frame;
    private final MasterCanvas masterCanvas;

    public MasterWindow() {
        frame = new JFrame(TITLE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        masterCanvas = new MasterCanvas();
        frame.add(masterCanvas.getCanvas());
        frame.pack();
        frame.setLocationRelativeTo(null);
        masterCanvas.startLoop();
    }

    public static void main(String[] args) {
        new MasterWindow();
    }

}
