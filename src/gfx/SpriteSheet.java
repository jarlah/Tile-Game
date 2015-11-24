package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class SpriteSheet {
    private BufferedImage image;
    
    private HashMap<String, BufferedImage> images = new HashMap<>();
    
    public SpriteSheet(String link) {
        try {
            image = ImageIO.read(SpriteSheet.class.getClassLoader().getResourceAsStream(link));
        } catch (IOException ex) {
            throw new RuntimeException("Could not load resource " + link, ex);
        }
    }
    
    public void loadCropped(String name, int x, int y, int pixelWidth, int pixelHeight) {
        images.put(name, image.getSubimage(x, y, pixelWidth, pixelHeight));
    }
    
    public BufferedImage getImage(String name) {
        return images.get(name);
    }
}
