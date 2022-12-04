package ooga.model.entities.livingentities;

import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.collidable.CollidableEntity;
import ooga.model.entities.info.Info;

public abstract class StaticCharacter extends CollidableEntity implements Alive {

  private int lives;

  /**
   * Static Entity has lives but can not move.
   * @param chart Collision Chart
   * @param initialXCoordinate initial x
   * @param initialYCoordinate initial y
   * @param height height
   * @param width width
   * @param entityInfo entity info
   */
  public StaticCharacter(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
    this.lives = setInitialLives(entityInfo);
  }

  /**
   * Returns number of lives of the current entity is alive. Extended by other interfaces.
   */
  @Override
  public int getLives() {
    return lives;
  }

  /**
   * This method should perform all actions necessary to kill the entity. This is specific to a
   * given entity, but for Mario this may include setting its velocities to 0 and disabling
   * abilities.
   */
  @Override
  public void kill() {
    lives--;
  }

  /**
   * allow characters to set their lives. Make sure lives cannot be negative
   *
   * @param lives value to which lives will now be set
   */
  protected void setLives(int lives) {
    if (lives <= 0) {
      this.lives = 0;
    } else {
      this.lives = lives;
    }
  }

}
