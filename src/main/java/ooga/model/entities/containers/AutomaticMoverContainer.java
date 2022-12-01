package ooga.model.entities.containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ooga.model.entities.AutomaticMovingEntity;
import ooga.model.entities.movement.AutomaticMover;
import ooga.model.entities.movement.Mover;

public class AutomaticMoverContainer implements Iterable<AutomaticMover>{

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
   * call move method on all Movers
   */
  public void moveAll(){
    for(AutomaticMover mover: this.movers){
      mover.automaticMove();
    }
  }

  @Override
  public Iterator<AutomaticMover> iterator() {
    return movers.iterator();
  }
}
