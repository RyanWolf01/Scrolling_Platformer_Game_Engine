package ooga.model.entities.containers;

import java.util.List;
import ooga.model.entities.Entity;

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

  }

}
