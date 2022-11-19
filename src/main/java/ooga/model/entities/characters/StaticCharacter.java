package ooga.model.entities.characters;

import ooga.model.entities.CollidableEntity;
import ooga.model.Info;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.movement.Mover;

public abstract class StaticCharacter extends CollidableEntity implements Alive {

  private int lives;

  private double xVelocity;
  private double yVelocity;

  public StaticCharacter(int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
//        this.lives = Integer.parseInt(entityInfo.get("lives"));
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
