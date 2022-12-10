package ooga.model.entities.livingentities;

import ooga.model.entities.info.ImmutableInfo;

public class AliveBehavior implements Alive{

  private int lives;

  public AliveBehavior(ImmutableInfo entityInfo){
    lives = setInitialLives(entityInfo);;
  }

  /**
   * Returns number of lives of the current entity is alive.
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
    changeLives(-1);
  }

  /**
   * Implements method in Alive interface that changes object's lives
   *
   * @param changeInLives is the change in lives
   */
  @Override
  public void changeLives(int changeInLives) {
    setLives(getLives() + changeInLives);
  }

  /**
   * allow characters to set their lives. Make sure lives cannot be negative
   *
   * @param lives value to which lives will now be set
   */
  private void setLives(int lives) {
    if (lives <= 0) {
      this.lives = 0;
    } else {
      this.lives = lives;
    }
  }



}
