package ooga.model.entities.deadmovingentities;

public class BasicMoverBehavior implements MoverBehavior{

  private double xVelocity;
  private double yVelocity;

  /**
   * add any additional movements in this behavior
   */
  @Override
  public void move() {
    // do nothing for basic mover, as the movement is done in the MovingEntity class
  }

  /**
   * Implements Mover interface changeVelocities method that changes object's velocities
   */
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
  public double getXVelocity() {
    return xVelocity;
  }

  /**
   * @return y velocity, implements Mover method This getter is needed for some MoverAction classes,
   * and for subclasses
   */
  public double getYVelocity() {
    return yVelocity;
  }


}
