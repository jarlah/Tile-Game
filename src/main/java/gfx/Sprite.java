package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Sprite {
    private static final int TILE_SIZE = 16;
    
	private final Map<String, SpriteWrapper> imageCache;
    private BufferedImage spriteSheet;
    private String link;
       
    public Sprite(String link) {
    	this.link = link;
        this.spriteSheet = null;
        this.imageCache = new HashMap<>();
    }
    
    public BufferedImage loadSprite(String link) {
    	BufferedImage sprite = null;
    	
    	try {
        	return sprite = ImageIO.read(Sprite.class.getClassLoader().getResourceAsStream(link));
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    	
    	return sprite;
    }
    
    public BufferedImage loadSprite() {
    	if (spriteSheet == null) {
    		spriteSheet = loadSprite(link);
    	}
    	return spriteSheet;
    }
    
    public BufferedImage getSprite(String name) {
    	return imageCache.get(name).img;
    }
    
    public BufferedImage loadSprite(String name, int x, int y) {
    	BufferedImage sprite = loadSprite();
    	
    	if (imageCache.containsKey(name) && imageCache.get(name).x == x && imageCache.get(name).y == y) {
    		return imageCache.get(name).img;
    	}
    	    	
    	BufferedImage img = sprite.getSubimage(x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
    	SpriteWrapper info = new SpriteWrapper(img, x, y);
    	imageCache.put(name, info);
    	
    	return img;
    }
    
    private class SpriteWrapper {
    	final BufferedImage img;
    	final int x, y;
    	
    	SpriteWrapper(BufferedImage img, int x, int y) {
    		this.img = img;
    		this.x = x;
    		this.y = y;
    	}
    }
}
