package ooga.model.entities.deadmovingentities;

public interface ImmutableMoverData {

  /**
   * get velocity to use on a right action
   * @return right action velocity
   */
  int getRightActionVelocity();

  /**
   * get velocity to use on a left action
   * @return left action velocity
   */
  int getLeftActionVelocity();

  /**
   * get velocity to use on an upward action
   * @return upward action velocity
   */
  int getUpwardActionVelocity();

  /**
   * get velocity to use on an downward action
   * @return downwardd action velocity
   */
  int getDownwardActionVelocity();

}
