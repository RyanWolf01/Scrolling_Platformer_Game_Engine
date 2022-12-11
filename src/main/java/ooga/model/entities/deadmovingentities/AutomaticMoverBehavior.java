package ooga.model.entities.deadmovingentities;

import java.util.ResourceBundle;
import ooga.controller.exceptions.MovementDataException;
import ooga.model.actions.moveractions.MoverAction;

public class AutomaticMoverBehavior extends BasicMoverBehavior{

  private MovingEntity thisEntity;
  private final MovementQueue movementQueue;
  private int stepCounter;
  private final int MOVEMENT_RATE;
  ResourceBundle entityInfoResources = ResourceBundle.getBundle("properties/movement");

  public AutomaticMoverBehavior(MovementQueue movementQueue, MovingEntity movingEntity){
    this.movementQueue = movementQueue;
    this.thisEntity = movingEntity;

    stepCounter = 0;

    try{
      MOVEMENT_RATE = Integer.parseInt(entityInfoResources.getString("automatic_movement_rate"));
    } catch(NullPointerException | NumberFormatException exception){
      throw new MovementDataException("no value provided for automatic movement rate in resources", exception);
    }
  }

  /**
   * This will move the entity automatically based on the configured MoverActions in the MoveQueue
   */
  @Override
  public void move(){

    try {
      MoverAction move = movementQueue.nextMove();

      if(shouldAutomaticMove())
        move.execute(thisEntity);
    }
    catch(NullPointerException exception){
      throw new MovementDataException("MovementQueue not initialized or empty.", exception);
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

}
