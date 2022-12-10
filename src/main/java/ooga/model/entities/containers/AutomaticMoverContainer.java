package ooga.model.entities.containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import ooga.model.collisions.physics.GravityChecker;
import ooga.model.entities.deadmovingentities.AutomaticMover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AutomaticMoverContainer implements Iterable<AutomaticMover>{

  Logger LOG = LogManager.getLogger(AutomaticMoverContainer.class);

  ResourceBundle entityInfoResources = ResourceBundle.getBundle("properties/movement");

  private final int MOVEMENT_RATE;
  private List<AutomaticMover> movers;
  private int stepCounter;
  /**
   * default constructor
   */
  public AutomaticMoverContainer(){
    movers = new ArrayList<>();
    stepCounter = 0;

    try{
      MOVEMENT_RATE = Integer.parseInt(entityInfoResources.getString("automatic_movement_rate"));
    } catch(NullPointerException | NumberFormatException exception){
      LOG.error("no value provided for automatic movement rate in resources");
      throw exception;
    }
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
      if(shouldAutomaticMove()) {
        mover.automaticMove();
      }
      mover.move();
    }
    stepCounter++;


  }

  /**
   * helper method to determine if should move automatic movers
   */
  private boolean shouldAutomaticMove(){
    if(stepCounter % MOVEMENT_RATE == 0)
      return true;
    return false;
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
