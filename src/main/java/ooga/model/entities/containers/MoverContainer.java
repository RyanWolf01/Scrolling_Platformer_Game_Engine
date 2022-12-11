package ooga.model.entities.containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ooga.model.entities.deadmovingentities.Mover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoverContainer implements Iterable<Mover>{

  Logger LOG = LogManager.getLogger(MoverContainer.class);

  private List<Mover> movers;
  /**
   * default constructor
   */
  public MoverContainer(){
    movers = new ArrayList<>();
  }

  /**
   * Add Mover
   */
  public void addMover(Mover mover){
    movers.add(mover);
  }

  /**
   * call move method on all Automatic Movers
   */
  public void moveAll(){
    for(Mover mover: this.movers){
      mover.move();
    }
  }

  /**
   * call reset velocities method on all Automatic Movers
   */
  public void resetVelocities(boolean resetXVelocity, boolean resetYVelocity){
    for(Mover mover: this.movers){
      mover.resetVelocities(resetXVelocity, resetYVelocity);
    }
  }

  @Override
  public Iterator<Mover> iterator() {
    return movers.iterator();
  }
}
