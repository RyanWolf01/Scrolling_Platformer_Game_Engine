package ooga.model.entities.containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ooga.model.entities.Entity;

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

  /**
   * If you want to add an entity upon creation of the container
   * @param entity automatically add this entity to newly created list entities
   */
  public EntityContainer(Entity entity){
    this();
    entities.add(entity);
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
