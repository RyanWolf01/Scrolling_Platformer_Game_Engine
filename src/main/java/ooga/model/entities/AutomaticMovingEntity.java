package ooga.model.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ooga.model.entities.data.Info;
import ooga.model.actionparsers.MoverActionParser;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.movement.AutomaticMover;
import ooga.model.entities.movement.MovementQueue;

public class AutomaticMovingEntity extends MovingEntity implements AutomaticMover {
  public static final List<String> AUTOMATIC_MOVING_ENTITY_TYPES = new ArrayList<>(Arrays.asList("goomba", "koopah"));

  private MovementQueue movementQueue;
  /**
   * AutomaticMovingCharacter has lives and can move; moves automatically
   * @param initialXCoordinate
   * @param initialYCoordinate
   * @param height
   * @param width
   * @param entityInfo
   */
  public AutomaticMovingEntity(int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo, MovementQueue movementQueue) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
    this.movementQueue = movementQueue;
  }

  /**
   * Implements Mover interface move method that changes object's position
   */
  @Override
  public void move() {
    setXCoordinate(getXCoordinate() + getXVelocity());
    setYCoordinate(getYCoordinate() + getYVelocity());
  }

  /**
   * This will move the entity automatically based on the configured MoverActions in the MoveQueue
   */
  @Override
  public void automaticMove(){
    MoverAction move = movementQueue.nextMove();
    move.execute(this);
  }

  @Override
  protected int performActions(ActionDataContainer actionDataContainer) {
    return new MoverActionParser(actionDataContainer).parseAndApplyActions(this);
  }

//  private void performMoverAction(ActionDataContainer actionDataContainer) {
//    MoverActionParser moverActionParser = new MoverActionParser(actionDataContainer);
//    if (moverActionParser.hasAction()) {
//      MoverAction moverAction = moverActionParser.getAction();
//      moverAction.execute(this);
//    }
//  }

}
