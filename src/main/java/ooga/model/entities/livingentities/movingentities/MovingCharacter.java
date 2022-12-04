package ooga.model.entities.livingentities.movingentities;

import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.deadmovingentities.MovingEntity;
import ooga.model.entities.info.Info;
import ooga.model.entities.livingentities.Alive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MovingCharacter extends MovingEntity implements Alive {

  private static final Logger LOG = LogManager.getLogger(MovingCharacter.class);
  private int lives;
  private double xVelocity;
  private double yVelocity;

  public MovingCharacter(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);

//        if(entityInfo.hasKey("lives")){
//          try {
//            this.lives = Integer.parseInt();
//          }
//        }

  }

  /**
   * Implements Mover interface changeVelocities method that changes object's velocities
   */
  @Override
  public void changeVelocities(double changeXVelocity, double changeYVelocity) {
    xVelocity += changeXVelocity;
    yVelocity += changeYVelocity;
  }

  /**
   * Reset velocities
   *
   * @param resetX tells if should reset xVelocity
   * @param resetY tells if should reset yVelocity
   *
   */
  @Override
  public void resetVelocities(boolean resetX, boolean resetY){
    if(resetX)
      xVelocity = 0;
    if(resetY)
      yVelocity = 0;
  }

  /**
   * @return x velocity, implements Mover method This getter is needed for some MoverAction classes,
   * and for subclasses
   */
  @Override
  public double getXVelocity() {
    return xVelocity;
  }

  /**
   * @return y velocity, implements Mover method This getter is needed for some MoverAction classes,
   * and for subclasses
   */
  @Override
  public double getYVelocity() {
    return yVelocity;
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
