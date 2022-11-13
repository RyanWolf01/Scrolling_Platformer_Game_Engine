package ooga.model.entities.alive;

/**
 * The LifeDecrementer interface extends the Alive interface, adding only the ability to decrement a life.
 * This method simply subtracts the passed in value from the current life count of the entity.
 */
public interface LifeDecrementer extends Alive {

  /**
   * Subtracts the passed value from the current life count of the entity, essentially decreasing the number of lives
   * of the entity.
   */
  public void decreaseLives(int lives);

}