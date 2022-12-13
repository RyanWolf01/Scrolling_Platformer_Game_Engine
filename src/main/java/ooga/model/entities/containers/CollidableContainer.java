package ooga.model.entities.containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ooga.model.entities.entitymodels.CollidableEntity;
import ooga.model.entities.entitymodels.Entity;

public class CollidableContainer implements Iterable<CollidableEntity>{

  private List<CollidableEntity> collidables;
  /**
   * default constructor
   */
  public CollidableContainer(){
    collidables = new ArrayList<>();
  }

  public boolean contains(Entity entity){
    return collidables.contains(entity);
  }

  /**
   * Add CollidableEntity to the container
   */
  public void addCollidable(CollidableEntity entity){
    collidables.add(entity);
  }

  public void remove(Entity entity){
    collidables.remove(entity);
  }

  @Override
  public Iterator<CollidableEntity> iterator() {
    return collidables.iterator();
  }


}
