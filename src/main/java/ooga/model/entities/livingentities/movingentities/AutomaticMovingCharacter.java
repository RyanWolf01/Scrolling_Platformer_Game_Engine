package ooga.model.entities.livingentities.movingentities;

import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.info.Info;
import ooga.model.actionparsers.AliveActionParser;
import ooga.model.actionparsers.MoverActionParser;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.deadmovingentities.AutomaticMover;
import ooga.model.entities.deadmovingentities.MovementQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AutomaticMovingCharacter extends MovingCharacter implements AutomaticMover {

  private static final Logger LOG = LogManager.getLogger(AutomaticMovingCharacter.class);
  private MovementQueue movementQueue;

  /**
   * AutomaticMovingCharacter has lives and can move; moves automatically
   * @param initialXCoordinate initial x
   * @param initialYCoordinate initial y
   * @param height height
   * @param width width
   * @param entityInfo entity info
   */
  public AutomaticMovingCharacter(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo, MovementQueue movementQueue) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
    this.movementQueue = movementQueue;
  }

  /**
   * helper method called in move() that handles what happens to this entity when it goes off the screen
   */
  @Override
  protected void handleInvalidCoordinates() { // allow characters to move off left, right, top and never come back
    if(getYCoordinate() >= getScreenSize()){
      kill();
      LOG.info("Character was killed.");
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

    try {
      MoverAction move = movementQueue.nextMove();
      move.execute(this);
    }
    catch(NullPointerException exception){
      LOG.error("MovementQueue not initialized or empty.");
      throw exception;
    }
  }

  @Override
  protected int performActions(ActionDataContainer actionDataContainer) {
    int count = 0;
    count += new MoverActionParser(actionDataContainer).parseAndApplyActions(this);
    count += new AliveActionParser(actionDataContainer).parseAndApplyActions(this);

    return count;
  }

//  private void performMoverAction(ActionDataContainer actionDataContainer) {
//    MoverActionParser moverActionParser = new MoverActionParser(actionDataContainer);
//    if (moverActionParser.hasAction()) {
//      MoverAction moverAction = moverActionParser.getAction();
//      moverAction.execute(this);
//    }
//  }
//
//  private void performAliveAction(ActionDataContainer actionDataContainer) {
//    AliveActionParser aliveActionParser = new AliveActionParser(actionDataContainer);
//    if (aliveActionParser.hasAction()) {
//      AliveAction aliveAction = aliveActionParser.getAction();
//      aliveAction.execute(this);
//    }
//  }

}
