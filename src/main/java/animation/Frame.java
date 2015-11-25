package animation;

import java.awt.image.BufferedImage;

import lombok.Data;

@Data
public class Frame {

    private BufferedImage frame;
    private int duration;

    public Frame(BufferedImage frame, int duration) {
        this.frame = frame;
        this.duration = duration;
    }
}
