package ooga.model.entities.alive;

public interface AliveBehavior extends ImmutableAliveBehavior{

  /**
   * Returns number of lives of the current entity is alive.
   */
  int getLives();

  /**
   * This method should perform all actions necessary to kill the entity. This is specific to a given entity, but
   * for Mario this may include setting its velocities to 0 and disabling abilities.
   */
  void kill();

  /**
   * Either increases or decreases lives of entity
   */
  void changeLives(int changeInLives);


}
