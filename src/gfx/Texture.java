package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.Getter;

public class Texture {
    @Getter
    private final BufferedImage image;
    
    public Texture(String link) {
        try {
            image = ImageIO.read(Texture.class.getClassLoader().getResourceAsStream(link));
        } catch (IOException ex) {
            throw new RuntimeException("Could not load texture " + link, ex);
        }
    }
}