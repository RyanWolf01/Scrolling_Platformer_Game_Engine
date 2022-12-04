package ooga.model.entities.livingentities.movingentities;

import java.text.ParseException;
import java.util.Properties;
import java.util.ResourceBundle;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.deadmovingentities.MovingEntity;
import ooga.model.entities.info.Info;
import ooga.model.entities.livingentities.Alive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MovingCharacter extends MovingEntity implements Alive {

  private static final Logger LOG = LogManager.getLogger(MovingCharacter.class);
  private int lives;

  /**
   * Moving Character has lives and can move.
   * @param chart Collision Chart
   * @param initialXCoordinate initial x
   * @param initialYCoordinate initial y
   * @param height height
   * @param width width
   * @param entityInfo entity info
   */
  public MovingCharacter(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height, double width,
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
