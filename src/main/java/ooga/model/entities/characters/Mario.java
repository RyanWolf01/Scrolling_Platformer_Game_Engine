package ooga.model.entities.characters;

import ooga.model.Info;
import ooga.model.entities.movement.Mover;

public class Mario extends MainCharacter implements Mover {

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
   * Implements method in Alive interface that changes object's lives
   * @param changeInLives is the change in lives
   */
  @Override
  public void changeLives(int changeInLives) {
    setLives(getLives() + changeInLives);
  }


}
