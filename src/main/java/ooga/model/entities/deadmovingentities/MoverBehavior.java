package ooga.model.entities.deadmovingentities;

public interface MoverBehavior {

  /**
   * This will move the Mover entity by its current velocity in whichever direction it should
   */
  void move();

  /**
   * @param changeYVelocity changes Mover's y velocity and updates position
   * @param changeXVelocity changes Mover's x velocity and updates position
   */
  void changeVelocities(double changeXVelocity, double changeYVelocity);

  /**
   * Reset velocities
   *
   * @param resetX tells if should reset xVelocity
   * @param resetY tells if should reset yVelocity
   *
   */
  void resetVelocities(boolean resetX, boolean resetY);

  /**
   *
   * @return double x velocity
   */
  double getXVelocity();

  /**
   *
   * @return double y velocity
   */
  double getYVelocity();


}
