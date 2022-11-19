package ooga.model.entities.movement;

/**
 * Implement automaticMove() in concrete class to automatically move entities that aren't main characters
 */
public interface AutomaticMover {

  /**
   * This will move the entity automatically based on the configured MoverActions in the MoverList
   */
  void automaticMove();

}
