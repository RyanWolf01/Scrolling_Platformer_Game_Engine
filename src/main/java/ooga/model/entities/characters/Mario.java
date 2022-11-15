package ooga.model.entities.characters;

import ooga.model.Info;
import ooga.model.entities.alive.LifeDecrementer;
import ooga.model.entities.alive.LifeIncrementer;
import ooga.model.entities.data.InitialAttributes;
import ooga.model.entities.movement.HorizontalMover;
import ooga.model.entities.movement.VerticalMover;

public class Mario extends MainCharacter implements HorizontalMover, VerticalMover,
    LifeDecrementer, LifeIncrementer {

  private int xVelocity;
  private int yVelocity;

  public Mario(int initialXCoordinate, int initialYCoordinate, double height, double width, Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
  }

  /**
   * Implements Mover interface move method that changes object's position
   */
  @Override
  public void move() {
    setXCoordinate(getXCoordinate() + xVelocity);
    setYCoordinate(getYCoordinate() + yVelocity);
  }

  /**
   * Implements method in Mover interface that changes object's y velocity
   */
  @Override
  public void incrementYVelocity(int change) {
    yVelocity += change;
  }

  /**
   * Implements method in Mover interface that changes object's x velocity
   */
  @Override
  public void incrementXVelocity(int change) {
    xVelocity = xVelocity + change;
  }

  /**
   * Implements method in Alive interface that increases object's lives
   */
  @Override
  public void increaseLives(int lives) {
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
