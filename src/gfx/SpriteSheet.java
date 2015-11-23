package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SpriteSheet {
    private BufferedImage image;
    private HashMap<String, BufferedImage> croppedImages = new HashMap<>();
    
    public SpriteSheet(String link) {
        try {
            image = ImageIO.read(SpriteSheet.class.getClassLoader().getResourceAsStream(link));
        } catch (IOException ex) {
            Logger.getLogger(SpriteSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadCropped(String name, int x, int y, int pixelWidth, int pixelHeight) {
        if (image != null) {
            croppedImages.put(name, image.getSubimage(x, y, pixelWidth, pixelHeight));
        }
    }
    
    public BufferedImage getCropped(String name) {
        return croppedImages.get(name);
    }
}
