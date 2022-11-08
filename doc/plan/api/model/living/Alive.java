/**
 * The LifeIncrementer interface extends the Alive interface, adding only the ability to increment a life.
 * This method simply adds the passed in value to the current life count of the entity.
 */
public interface LifeIncrementer extends Alive {

  /**
   * Adds the passed value to the current life count of the entity, essentially increasing the number of lives
   * of the entity.
   */
  public boolean addLives(int lives);

}