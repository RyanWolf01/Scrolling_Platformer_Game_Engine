package ooga.model.entities.livingentities.movingentities.maincharacters;

import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.livingentities.movingentities.MovingCharacter;
import ooga.model.entities.info.Info;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Maybe all main character entities
 */
public abstract class MainCharacterEntity extends MovingCharacter implements UserControllable {

  private static final Logger LOG = LogManager.getLogger(MainCharacterEntity.class);

  /**
   * MainCharacterEntity takes user input and is alive, collidable, and moveable
   * @param chart Collision Chart
   * @param initialXCoordinate initial x
   * @param initialYCoordinate initial y
   * @param height height
   * @param width width
   * @param entityInfo entity info
   */
  public MainCharacterEntity(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height,
      double width, Info entityInfo) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
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
}
