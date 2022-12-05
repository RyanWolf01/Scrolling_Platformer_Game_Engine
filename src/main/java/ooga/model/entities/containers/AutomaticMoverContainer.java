package ooga.model.entities.containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ooga.model.entities.deadmovingentities.AutomaticMover;

public class AutomaticMoverContainer implements Iterable<AutomaticMover>{

  private List<AutomaticMover> movers;
  /**
   * default constructor
   */
  public AutomaticMoverContainer(){
    movers = new ArrayList<>();
  }

  /**
   * Add Mover
   */
  public void addMover(AutomaticMover mover){
    movers.add(mover);
  }

  /**
   * call move method on all Automatic Movers
   */
  public void moveAll(){
    for(AutomaticMover mover: this.movers){
      mover.automaticMove();
    }
  }

  /**
   * call reset velocities method on all Automatic Movers
   */
  public void resetVelocities(boolean resetXVelocity, boolean resetYVelocity){
    for(AutomaticMover mover: this.movers){
      mover.resetVelocities(resetXVelocity, resetYVelocity);
    }
  }

  @Override
  public Iterator<AutomaticMover> iterator() {
    return movers.iterator();
  }
}
