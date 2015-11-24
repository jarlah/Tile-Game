package entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class EntityHandler {
    public static EntityHandler get() { 
        return Creator.object;
    }

    private static class Creator {
        private static final EntityHandler object = new EntityHandler();
    }
    
    private EntityHandler() {}

    private final List<Entity> entities = new ArrayList<>();

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    public void removeAll() {
        entities.removeAll(entities);
    }
    
    public void render(Graphics g) {
        entities.stream().forEach((e) -> e.render(g));
    }
    
    public void tick(double delta) {
        entities.stream().forEach((e) -> e.tick(delta));
    }

	public List<Entity> getEntities() {
		return entities;
	}
}
