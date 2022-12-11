package ooga.model.entities.livingentities.movingentities.maincharacters;

import ooga.model.actionparsers.AliveActionParser;
import ooga.model.actionparsers.EndGameActionParser;
import ooga.model.actionparsers.MoverActionParser;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.deadmovingentities.MovementQueue;
import ooga.model.entities.livingentities.movingentities.MovingCharacter;
import ooga.model.entities.info.Info;
import ooga.model.entities.modelcallers.GameEnder;
import ooga.model.entities.modelcallers.functionalinterfaces.EndGameCallable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Maybe all main character entities
 */
public class MainCharacter extends MovingCharacter implements UserControllable,
    GameEnder {

  private static final Logger LOG = LogManager.getLogger(MainCharacter.class);
  private EndGameCallable endGameMethod;

  /**
   * MainCharacterEntity takes user input and is alive, collidable, and moveable
   * @param chart Collision Chart
   * @param initialXCoordinate initial x
   * @param initialYCoordinate initial y
   * @param height height
   * @param width width
   * @param entityInfo entity info
   */
  public MainCharacter(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height,
      double width, Info entityInfo, MovementQueue movementQueue) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo, movementQueue);
  }

  /**
   * accept and execute move action
   * @param action MoverAction
   */
  @Override
  public void acceptMoveAction(MoverAction action) {
    try {
      action.execute(this);
    }catch(NullPointerException exception){
      LOG.error("MoverAction is null");
      throw exception;
    }
  }

  /**
   * accept and execute alive action
   * @param action AliveAction
   */
  @Override
  public void acceptAliveAction(AliveAction action) {
    try {
      action.execute(this);
    }catch(NullPointerException exception){
      LOG.error("AliveAction is null");
      throw exception;
    }
  }

  /**
   * reads from CollisionChart and performs resulting actions necessary to handle the collision
   *
   * @param actionDataContainer
   */
  @Override
  public int performActions(ActionDataContainer actionDataContainer) {
    int count = 0;
    count += new MoverActionParser(actionDataContainer).parseAndApplyActions(this);
    count += new AliveActionParser(actionDataContainer).parseAndApplyActions(this);
    count += new EndGameActionParser(actionDataContainer).parseAndApplyActions(this);

    return count;
  }

  public void endGame() {
    if (endGameMethod == null) throw new RuntimeException("The end game method for mario hasn't"
        + "been set!");
    endGameMethod.execute();
  }

  public void setEndGameCallable(EndGameCallable endGameCallable) {
    this.endGameMethod = endGameCallable;
  }

  private boolean isEndGameMethodSet() {
    return endGameMethod != null;
  }
}
