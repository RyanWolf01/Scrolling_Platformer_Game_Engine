package ooga.model.entities.deadmovingentities;

import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.info.Info;
import ooga.model.actionparsers.MoverActionParser;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.actiondata.ActionDataContainer;

public class AutomaticMovingEntity extends MovingEntity implements AutomaticMover {
  private MovementQueue movementQueue;
  /**
   * AutomaticMovingCharacter has lives and can move; moves automatically
   * @param initialXCoordinate
   * @param initialYCoordinate
   * @param height
   * @param width
   * @param entityInfo
   */
  public AutomaticMovingEntity(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo, MovementQueue movementQueue) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
    this.movementQueue = movementQueue;
  }

  /**
   * helper method called in move() that handles what happens to this entity when it goes off the screen
   */
  @Override
  protected void handleInvalidCoordinates() {
    // allow entities to move off left, right, top, bottom and never come back
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

  @Override
  public boolean canMoveLeft() {
    return !isInAir();
  }

  @Override
  public boolean canMoveRight() {
    return !isInAir();
  }

}
