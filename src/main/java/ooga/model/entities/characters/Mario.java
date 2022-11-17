package ooga.model.entities.characters;

import ooga.model.Info;
import ooga.model.actionparsers.AliveActionParser;
import ooga.model.actionparsers.MoverActionParser;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.data.ActionDataContainer;
import ooga.model.entities.movement.Mover;

public class Mario extends MainCharacter implements Mover {

  public Mario(int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
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
   * Implements method in Alive interface that changes object's lives
   *
   * @param changeInLives is the change in lives
   */
  @Override
  public void changeLives(int changeInLives) {
    setLives(getLives() + changeInLives);
  }

  /**
   * reads from CollisionChart and performs resulting actions necessary to handle the collision
   *
   * @param actionDataContainer
   */
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
