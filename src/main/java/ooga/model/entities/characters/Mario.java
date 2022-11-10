package ooga.model.entities.characters;

import ooga.model.entities.alive.LifeDecrementer;
import ooga.model.entities.alive.LifeIncrementer;
import ooga.model.entities.data.InitialAttributes;
import ooga.model.entities.movement.HorizontalMover;
import ooga.model.entities.movement.VerticalMover;

public class Mario extends MainCharacter implements HorizontalMover, VerticalMover,
    LifeDecrementer, LifeIncrementer {

  public Mario(InitialAttributes attributes) {
    super(attributes);
  }

  @Override
  public void move() {
    incrementXCoordinate(xVelocity);
    incrementYCoordinate(yVelocity);
  }

  @Override
  public void incrementYVelocity(int change) {
    yVelocity += change;
  }

  @Override
  public int getYVelocity() {
    return yVelocity;
  }
  @Override
    public void incrementXVelocity(int change) {
        xVelocity = xVelocity+change;
    }


    @Override
    public void increaseLives(int lives) {
        setLives(getLives() + lives);
    }

  @Override
  public void decreaseLives(int lives) {
    setLives(getLives() - lives);
  }

}
