package entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public enum EntityHandler {
    Singleton;

    @Getter
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
}
