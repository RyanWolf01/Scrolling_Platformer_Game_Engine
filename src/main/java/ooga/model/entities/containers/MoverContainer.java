package ooga.model.entities.containers;

import ooga.model.entities.Entity;
import ooga.model.entities.movement.Mover;

public class MoverContainer extends EntityContainer {

  /**
   * default constructor
   */
  public MoverContainer(){
    super();
  }

  /**
   * If you want to add an entity upon creation of the container
   * @param entity
   */
  public MoverContainer(Entity entity){
    super(entity);
  }

  /**
   * call move method on all Movers
   */
  public void moveAll(){

    try {
      for (int index = 0; index < getContainerSize(); index++) {
        Mover currMover = (Mover) getEntity(index);
        currMover.move();
      }
    }
    catch(ClassCastException exception){
      throw exception;
    }
  }

}
