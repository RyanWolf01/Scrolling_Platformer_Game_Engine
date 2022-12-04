package ooga.model.entities.deadmovingentities;

import java.util.ResourceBundle;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.collisions.physics.PhysicsCalculator;
import ooga.model.entities.collidable.CollidableEntity;
import ooga.model.entities.info.Info;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MovingEntity extends CollidableEntity implements Mover {

  private static final Logger LOG = LogManager.getLogger(MovingEntity.class);
  private static final int SCREEN_SIZE = Integer.parseInt(
      ResourceBundle.getBundle("properties/view").getString("screen_size"));
  private static final double GRAVITY_VELOCITY = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("gravity_velocity"));

  private double xVelocity;
  private double yVelocity;

  public MovingEntity(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
  }

  /**
   * Implements Mover interface move method that changes object's position
   */
  @Override
  public void move() {
    applyGravity();
    setXCoordinate(getXCoordinate() + getXVelocity());
    setYCoordinate(getYCoordinate() + getYVelocity());
    handleInvalidCoordinates();
  }

  /**
   * helper method for move that makes sure new coordinates are valid. if not, handles cases appropriately.
   * must be implemented in each concrete class.
   */
  protected abstract void handleInvalidCoordinates();

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
   *
   * @return screen size
   */
  protected int getScreenSize(){
    return SCREEN_SIZE;
  }

  protected void applyGravity(){
    if(isInAir())
      changeVelocities(0, GRAVITY_VELOCITY);
  }

  /**
   * returns if entity is in air
   * @return boolean
   */
  private boolean isInAir(){
    PhysicsCalculator physicsCalculator = new PhysicsCalculator();
    return physicsCalculator.checkInAir(this);
  }

}
