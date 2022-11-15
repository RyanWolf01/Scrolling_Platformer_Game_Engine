package ooga.model.entities.characters;

import ooga.model.Info;
import ooga.model.entities.movement.Mover;

public class Mario extends MainCharacter implements Mover {

  private int xVelocity;
  private int yVelocity;

  public Mario(int initialXCoordinate, int initialYCoordinate, double height, double width, Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
  }

  /**
   * Implements Mover interface move method that changes object's position
   */
  @Override
  public void move(int changeXVelocity, int changeYVelocity) {
    setXCoordinate(getXCoordinate() + xVelocity);
    setYCoordinate(getYCoordinate() + yVelocity);
  }

  /**
   * Implements method in Alive interface that increases object's lives
   */
  @Override
  public void changeLives(int lives) {
    setLives(getLives() + lives);
  }

  /**
   * Implements method in Alive interface that decreases object's lives
   */
  @Override
  public void decreaseLives(int lives) {
    setLives(getLives() - lives);
  }

}
