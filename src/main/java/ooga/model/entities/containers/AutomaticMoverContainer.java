package ooga.model.entities.containers;

import java.util.ArrayList;
import java.util.List;
import ooga.model.entities.AutomaticMovingEntity;
import ooga.model.entities.movement.AutomaticMover;
import ooga.model.entities.movement.Mover;

public class AutomaticMoverContainer {

  private List<AutomaticMover> movers;
  /**
   * default constructor
   */
  public AutomaticMoverContainer(){
    movers = new ArrayList<>();
  }

  /**
   * If you want to add an entity upon creation of the container
   * @param mover
   */
  public AutomaticMoverContainer(AutomaticMover mover){
    movers = new ArrayList<>();
    movers.add(mover);
  }

  /**
   * Add Mover
   */
  public void addMover(AutomaticMover mover){
    movers.add(mover);
  }

  /**
   * Get ImmutableEntity at index
   * @param index index in container
   * @return ImmutableEntity
   */
  private AutomaticMover getMover(int index){
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
      AutomaticMover currMover = getMover(index);
      currMover.automaticMove();
    }
  }

}
