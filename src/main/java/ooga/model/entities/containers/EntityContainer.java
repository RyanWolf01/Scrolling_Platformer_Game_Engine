package ooga.model.entities.containers;

import ooga.model.entities.Entity;

/**
 * hold all entities
 */
public class EntityContainer extends Container {

  /**
   * default constructor
   */
  public EntityContainer(){
    super();
  }

  /**
   * If you want to add an entity upon creation of the container
   * @param entity
   */
  public EntityContainer(Entity entity){
    super(entity);
  }

}
