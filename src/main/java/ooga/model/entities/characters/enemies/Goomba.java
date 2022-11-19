package ooga.model.entities.characters.enemies;

import ooga.model.Info;
import ooga.model.entities.CollidableEntity;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.movement.Mover;

public class Goomba extends CollidableEntity implements Mover, Alive {



  /**
   * Goomba is an Enemy that can Move and have Lives
   * @param initialXCoordinate
   * @param initialYCoordinate
   * @param height
   * @param width
   * @param entityInfo
   */
  public Goomba(int initialXCoordinate, int initialYCoordinate, double height, double width,
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
    if(getYCoordinate() >= SCREEN_SIZE){
      kill();
    }
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

}
