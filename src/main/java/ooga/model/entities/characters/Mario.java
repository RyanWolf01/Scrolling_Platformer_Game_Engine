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

  /**
   * Constructor for Mario class that calls superclass MainCharacter constructor
   * @param attributes initial conditions (x, y positions)
   * @param entityInfo information specific to subclass
   * @param length length of Mario (in y direction)
   * @param width width of Mario (in x direction)
   * @param lives initial lives
   */
  public Mario(InitialAttributes attributes, Info entityInfo, double length, double width, int lives) {
    super(attributes, entityInfo, length, width, lives);
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
