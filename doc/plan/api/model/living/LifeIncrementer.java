/**
 * The Alive interface is used to distinguish between an entity that holds a concept of being alive (i.e. being
 * able to be destroyed) and one whose existence can never be altered. This interface is a broad one that is extended
 * by more specific interfaces based on the functionality of the entity.
 */
public interface Alive {

  /**
   * Returns a boolean representing if the current entity is alive. Extended by other
   * interfaces (LifeDecrementer.java and LifeIncrementer)
   */
  public boolean isAlive();

  /**
   * This method should set the boolean isAlive to false in addition to performing all other
   * actions necessary to kill the entity
   */
  public void kill();

}