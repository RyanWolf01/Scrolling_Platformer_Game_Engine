package ooga.model.entities.containers;

import java.util.ArrayList;
import java.util.List;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntity;

/**
 * hold all entities
 */
public class EntityContainer implements ImmutableEntityContainer{

  private List<Entity> entities;

  public EntityContainer(){
    entities = new ArrayList<>();
  }

  /**
   * Get ImmutableEntity at index
   * @param index index in container
   * @return ImmutableEntity
   */
  public ImmutableEntity getEntity(int index){
    return entities.get(index);
  }

  /**
   * Add Entity
   */
  public void addEntity(Entity entity){
    entities.add(entity);
  }

}
