package ooga.model.entities.containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ooga.model.entities.entitymodels.Entity;

/**
 * hold all entities
 */
public class EntityContainer implements Iterable<Entity> {
  private List<Entity> entities;

  /**
   * default constructor
   */
  public EntityContainer(){
    entities = new ArrayList<>();
  }

  public void addEntity(Entity entity){
    entities.add(entity);
  }

  public boolean contains(Entity entity){
    return entities.contains(entity);
  }

  @Override
  public Iterator<Entity> iterator() {
    return entities.iterator();
  }
}
