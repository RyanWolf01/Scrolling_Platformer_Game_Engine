package ooga.model.entities.containers;

import java.util.ArrayList;
import java.util.List;
import ooga.model.entities.Entity;
import ooga.model.entities.EntityInfo;
import ooga.model.entities.ImmutableEntity;
import ooga.model.entities.movement.Mover;

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
