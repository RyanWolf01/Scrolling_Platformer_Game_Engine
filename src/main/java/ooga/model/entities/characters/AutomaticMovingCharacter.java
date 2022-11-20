package ooga.model.entities.characters;

import ooga.model.entities.data.Info;
import ooga.model.actionparsers.AliveActionParser;
import ooga.model.actionparsers.MoverActionParser;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.data.ActionDataContainer;
import ooga.model.entities.movement.AutomaticMover;
import ooga.model.entities.movement.MovementQueue;

public class AutomaticMovingCharacter extends MovingCharacter implements AutomaticMover {

  private MovementQueue movementQueue;
  /**
   * AutomaticMovingCharacter has lives and can move; moves automatically
   * @param initialXCoordinate
   * @param initialYCoordinate
   * @param height
   * @param width
   * @param entityInfo
   */
  public AutomaticMovingCharacter(int initialXCoordinate, int initialYCoordinate, double height, double width,
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
    if(getYCoordinate() >= getScreenSize()){
      kill();
    }
  }

  /**
   * Implements method in Alive interface that changes object's lives
   *
   * @param changeInLives is the change in lives
   */
  @Override
  public void changeLives(int changeInLives) {
    setLives(getLives() + changeInLives);
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
  public void performActions(ActionDataContainer actionDataContainer) {
    performAliveAction(actionDataContainer);
    performMoverAction(actionDataContainer);
  }

  private void performMoverAction(ActionDataContainer actionDataContainer) {
    MoverActionParser moverActionParser = new MoverActionParser(actionDataContainer);
    if (moverActionParser.hasAction()) {
      MoverAction moverAction = moverActionParser.getAction();
      moverAction.execute(this);
    }
  }

  private void performAliveAction(ActionDataContainer actionDataContainer) {
    AliveActionParser aliveActionParser = new AliveActionParser(actionDataContainer);
    if (aliveActionParser.hasAction()) {
      AliveAction aliveAction = aliveActionParser.getAction();
      aliveAction.execute(this);
    }
  }

}
