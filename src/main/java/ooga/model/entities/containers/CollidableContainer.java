package ooga.model.entities.containers;

import java.util.ArrayList;
import java.util.List;
import ooga.model.entities.CollidableEntity;
import ooga.model.entities.Entity;
import ooga.model.entities.movement.AutomaticMover;

public class CollidableContainer{

  private List<CollidableEntity> collidables;
  /**
   * default constructor
   */
  public CollidableContainer(){
    collidables = new ArrayList<>();
  }


  /**
   * Add CollidableEntity to the container
   */
  public void addCollidable(CollidableEntity entity){
    collidables.add(entity);
  }

  public boolean contains(Entity entity){
    return collidables.contains(entity);
  }
}
