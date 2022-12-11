package ooga.model.entities.deadmovingentities;

import ooga.controller.exceptions.MovementDataException;
import ooga.model.actions.moveractions.MoverAction;

public class AutomaticMoverBehavior extends BasicMoverBehavior{

  private MovingEntity thisEntity;
  private final MovementQueue movementQueue;

  public AutomaticMoverBehavior(MovementQueue movementQueue, MovingEntity movingEntity){
    this.movementQueue = movementQueue;
    this.thisEntity = movingEntity;
  }

  /**
   * This will move the entity automatically based on the configured MoverActions in the MoveQueue
   */
  @Override
  public void move(){

    try {
      MoverAction move = movementQueue.nextMove();
      move.execute(thisEntity);
    }
    catch(NullPointerException exception){
      throw new MovementDataException("MovementQueue not initialized or empty.", exception);
    }
  }

}
