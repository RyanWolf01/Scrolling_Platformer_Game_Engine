package ooga.model.entities.deadmovingentities;

public interface ImmutableMoverData {

  /**
   * get velocity to use on a right action
   * @return right action velocity
   */
  double getRightActionVelocity();

  /**
   * get velocity to use on a left action
   * @return left action velocity
   */
  double getLeftActionVelocity();

  /**
   * get velocity to use on an upward action
   * @return upward action velocity
   */
  double getUpwardActionVelocity();

  /**
   * get velocity to use on an downward action
   * @return downwardd action velocity
   */
  double getDownwardActionVelocity();

  /**
   * get velocity to use on an downward action
   * @return downwardd action velocity
   */
  double getGravityVelocity();

}
