package ooga.model.entities.characters;

import ooga.model.entities.alive.LifeDecrementer;
import ooga.model.entities.alive.LifeIncrementer;
import ooga.model.entities.data.InitialAttributes;
import ooga.model.entities.movement.HorizontalMover;
import ooga.model.entities.movement.VerticalMover;

public class Mario extends MainCharacter implements HorizontalMover, VerticalMover,
    LifeDecrementer, LifeIncrementer {

  private int xVelocity;
  private int yVelocity;

  public Mario(InitialAttributes attributes) {
    super(attributes);
  }

  @Override
  public void move() {
    setXCoordinate(getXCoordinate() + xVelocity);
    setYCoordinate(getYCoordinate() + yVelocity);
  }

  @Override
  public void incrementYVelocity(int change) {
    yVelocity += change;
  }

  @Override
  public void incrementXVelocity(int change) {
    xVelocity = xVelocity + change;
  }


  @Override
  public void increaseLives(int lives) {
    setLives(getLives() + lives);
  }

  @Override
  public void decreaseLives(int lives) {
    if(getLives() == 0) // check if try to decrease lives below 0
      lives = 0;
    setLives(getLives() - lives);
  }

}
