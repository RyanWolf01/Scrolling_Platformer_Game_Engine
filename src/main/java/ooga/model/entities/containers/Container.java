package ooga.model.entities.containers;

import java.util.ArrayList;
import java.util.List;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntity;

public abstract class Container implements ImmutableContainer {

  private List<Entity> entities;

  /**
   * default constructor
   */
  public Container(){
    entities = new ArrayList<>();
  }

  /**
   * If you want to add an entity upon creation of the container
   * @param entity
   */
  public Container(Entity entity){
    entities = new ArrayList<>();
    entities.add(entity);
  }

  /**
   * Add Entity
   */
  public void addEntity(Entity entity){
    entities.add(entity);
  }

  /**
   * Get ImmutableEntity at index
   * @param index index in container
   * @return ImmutableEntity
   */
  public ImmutableEntity getEntity(int index){
    return getEntity(index);
  }

}