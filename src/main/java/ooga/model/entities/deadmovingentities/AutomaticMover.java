package ooga.model.entities.deadmovingentities;

/**
 * Implement automaticMove() in concrete class to automatically move entities that aren't main characters
 */
public interface AutomaticMover extends Mover{

  /**
   * This will move the entity automatically based on the configured MoverActions in the MovementQueue
   */
  void automaticMove();

}
