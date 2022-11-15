package ooga.model.entities.containers;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntity;
import ooga.model.entities.movement.Mover;

public class MoverContainer {

  private List<Mover> movers;
  /**
   * default constructor
   */
  public MoverContainer(){
    movers = new ArrayList<>();
  }

  /**
   * If you want to add an entity upon creation of the container
   * @param mover
   */
  public MoverContainer(Mover mover){
    movers = new ArrayList<>();
    movers.add(mover);
  }

  /**
   * Add Mover
   */
  public void addMover(Mover mover){
    movers.add(mover);
  }

  /**
   * Get ImmutableEntity at index
   * @param index index in container
   * @return ImmutableEntity
   */
  public Mover getMover(int index){
    return movers.get(index);
  }

  /**
   *
   * @return size of container
   */
  public int getContainerSize(){
    return movers.size();
  }

  /**
   * call move method on all Movers
   */
  public void moveAll(){
    for (int index = 0; index < getContainerSize(); index++) {
      Mover currMover = getMover(index);
      currMover.move();
    }
  }

}
