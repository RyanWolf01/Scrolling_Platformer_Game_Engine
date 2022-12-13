package ooga.model.entities.entitymodels;

import ooga.model.entities.maincharacter.MainCharacterState;
import ooga.model.actionparsers.AliveActionParser;
import ooga.model.actionparsers.EntityActionParser;
import ooga.model.actionparsers.MainCharacterActionParser;
import ooga.model.actionparsers.MoverActionParser;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.extrainterfaces.UserControllable;
import ooga.model.entities.maincharacter.BasicMainCharacterBehavior;
import ooga.model.entities.maincharacter.ImmutableMainCharacterBehavior;
import ooga.model.entities.maincharacter.MainCharacter;
import ooga.model.entities.maincharacter.MainCharacterBehavior;
import ooga.model.entities.movement.MovementQueue;
import ooga.model.entities.info.Info;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Maybe all main character entities
 */
public class BasicMainCharacter extends MovingCharacter implements UserControllable, MainCharacter {

  private static final Logger LOG = LogManager.getLogger(BasicMainCharacter.class);
  private MainCharacterBehavior mainCharacterBehavior;


  /**
   * MainCharacterEntity takes user input and is alive, collidable, and moveable
   * @param chart Collision Chart
   * @param initialXCoordinate initial x
   * @param initialYCoordinate initial y
   * @param height height
   * @param width width
   * @param entityInfo entity info
   */
  public BasicMainCharacter(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height,
      double width, Info entityInfo, MovementQueue movementQueue) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo, movementQueue);
    mainCharacterBehavior = new BasicMainCharacterBehavior(getAliveBehavior());
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
      checkLivesAndUpdateMainCharacterState();
    }catch(NullPointerException exception){
      LOG.error("AliveAction is null");
      throw exception;
    }
  }

  /**
   * reads from CollisionChart and performs resulting actions necessary to handle the collision
   *
   * @param actionDataContainer contains all the actions to be performed on this entity
   */
  @Override
  public int performActions(ActionDataContainer actionDataContainer) {
    int count = 0;
    count += new MoverActionParser(actionDataContainer).parseAndApplyActions(this);
    count += new AliveActionParser(actionDataContainer).parseAndApplyActions(this);
    count += new EntityActionParser(actionDataContainer).parseAndApplyActions(this);
    count += new MainCharacterActionParser(actionDataContainer).parseAndApplyActions(this);

    return count;
  }

  /**
   *
   * @return get MainCharacter state
   */
  @Override
  public MainCharacterState getMainCharacterState() {
    return mainCharacterBehavior.getMainCharacterState();
  }

  /**
   * check lives and alter state accordingly
   */
  @Override
  public void checkLivesAndUpdateMainCharacterState() {
    mainCharacterBehavior.checkLivesAndUpdateMainCharacterState();
  }

  /**
   *
   * @param mainCharacterState new main character state
   */
  @Override
  public void setMainCharacterState(MainCharacterState mainCharacterState) {
    mainCharacterBehavior.setMainCharacterState(mainCharacterState);
  }

  /**
   *
   * @param addToScore to add to score
   */
  @Override
  public void updateScore(int addToScore) {
    mainCharacterBehavior.updateScore(addToScore);
  }

  /**
   *
   * @return score
   */
  @Override
  public int getScore() {
    return mainCharacterBehavior.getScore();
  }

  /**
   *
   * @param mainCharacterBehavior new main character behavior
   */
  @Override
  public void setMainCharacterBehavior(MainCharacterBehavior mainCharacterBehavior) {
    this.mainCharacterBehavior = mainCharacterBehavior;
  }

  /**
   *
   * @return immutable version of main character behavior
   */
  @Override
  public ImmutableMainCharacterBehavior getMainCharacterBehavior() {
    return mainCharacterBehavior;
  }

  /**
   *
   * @return if has power up
   */
  @Override
  public boolean hasPowerUp(){
    return mainCharacterBehavior.hasPowerUp();
  }

  /**
   * set has power up
   */
  @Override
  public void setHasPowerUp(boolean hasPowerUp){
    mainCharacterBehavior.setHasPowerUp(hasPowerUp);
  }
}
