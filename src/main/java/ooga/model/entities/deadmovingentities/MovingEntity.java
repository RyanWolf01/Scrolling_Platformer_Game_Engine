package ooga.model.entities.deadmovingentities;

import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.collidable.CollidableEntity;
import ooga.model.entities.info.Info;

public abstract class MovingEntity extends CollidableEntity implements Mover {

  private static final int SCREEN_SIZE = 0; // TODO read from Properties file
  private double xVelocity;
  private double yVelocity;

  public MovingEntity(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
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
   *
   * @return screen size
   */
  protected int getScreenSize(){
    return SCREEN_SIZE;
  }

}
