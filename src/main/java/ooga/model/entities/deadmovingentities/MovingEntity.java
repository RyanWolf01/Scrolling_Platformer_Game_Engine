package ooga.model.entities.deadmovingentities;

import java.util.ResourceBundle;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.collisions.physics.GravityChecker;
import ooga.model.entities.collidable.CollidableEntity;
import ooga.model.entities.info.Info;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MovingEntity extends CollidableEntity implements Mover {

  private static final Logger LOG = LogManager.getLogger(MovingEntity.class);

  private MoverBehavior moverBehavior;

  /**
   * Moving Entity has no lives but can move. Constructor initializes screen size from Properties files.
   * @param chart Collision Chart
   * @param initialXCoordinate initial x
   * @param initialYCoordinate initial y
   * @param height height
   * @param width width
   * @param entityInfo entity info
   */
  public MovingEntity(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);

  }

  /**
   * Implements Mover interface move method that changes object's position
   */
  @Override
  public void move() {
    setXCoordinate(getXCoordinate() + moverBehavior.getXVelocity());
    setYCoordinate(getYCoordinate() + moverBehavior.getYVelocity());
  }

  /**
   * Implements Mover interface changeVelocities method that changes object's velocities
   */
  @Override
  public void changeVelocities(double changeXVelocity, double changeYVelocity) {
    moverBehavior.changeVelocities(changeXVelocity, changeYVelocity);
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
    moverBehavior.resetVelocities(resetX, resetY);
  }

  /**
   * @return x velocity, implements Mover method This getter is needed for some MoverAction classes,
   * and for subclasses
   */
  @Override
  public double getXVelocity() {
    return moverBehavior.getXVelocity();
  }

  /**
   * @return y velocity, implements Mover method This getter is needed for some MoverAction classes,
   * and for subclasses
   */
  @Override
  public double getYVelocity() {
    return moverBehavior.getYVelocity();
  }

  /**
   * returns if entity is in air
   * @return boolean
   */
  @Override
  public boolean isInAir(){
    GravityChecker gravityChecker = new GravityChecker();
    return gravityChecker.checkInAir(this);
  }

  @Override
  public boolean isHittingLeftOfPlatform() {
    GravityChecker gravityChecker = new GravityChecker();
    return gravityChecker.checkHittingLeftOfPlatform(this);
  }

  @Override
  public boolean isHittingRightOfPlatform() {
    GravityChecker gravityChecker = new GravityChecker();
    return gravityChecker.checkHittingRightOfPlatform(this);
  }

}
