package ooga.model.entities.characters.maincharacters;

import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.UserControllable;
import ooga.model.entities.characters.MovingCharacter;
import ooga.model.entities.data.Info;

/**
 * Maybe all main character entities
 */
public class MainCharacterEntity extends MovingCharacter implements UserControllable {

  public MainCharacterEntity(int initialXCoordinate, int initialYCoordinate, double height,
      double width, Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
  }

  @Override
  public void acceptMoveAction(MoverAction action) {

  }

  @Override
  public void acceptAliveAction(AliveAction action) {

  }

  @Override
  public int getLives() {
    return 0;
  }

  @Override
  public void kill() {

  }

  @Override
  public void changeLives(int changeInLives) {

  }

  @Override
  public void move() {

  }

  @Override
  public void changeVelocities(double changeXVelocity, double changeYVelocity) {

  }

  @Override
  public void resetVelocities(boolean resetX, boolean resetY) {

  }

  @Override
  public double getXVelocity() {
    return 0;
  }

  @Override
  public double getYVelocity() {
    return 0;
  }

  @Override
  protected int performActions(ActionDataContainer actionDataContainer) {
    return 0;
  }
}
