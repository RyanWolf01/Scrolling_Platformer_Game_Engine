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
  private final int SCREEN_SIZE;
  private double xVelocity;
  private double yVelocity;

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

    int tempScreenSize;
    try{
      tempScreenSize = Integer.parseInt(
          ResourceBundle.getBundle("properties/view").getString("screen_size"));
    } catch(NumberFormatException exception){
      LOG.error("screen size from properties file formatted incorrectly");
      throw exception;
    }

    SCREEN_SIZE = tempScreenSize;

  }

  /**
   * Implements Mover interface move method that changes object's position
   */
  @Override
  public void move() {
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
