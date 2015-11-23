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
            System.out.println(image);
        } catch (IOException ex) {
            throw new RuntimeException("Could not load resource " + link, ex);
        }
    }
    
    public void loadCropped(String name, int x, int y, int pixelWidth, int pixelHeight) {
        BufferedImage subImage = image.getSubimage(x, y, pixelWidth, pixelHeight);
        System.out.println(subImage);
        images.put(name, subImage);
    }
    
    public BufferedImage getImage(String name) {
        return images.get(name);
    }
}
