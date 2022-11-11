package ooga.model.collisions.collision_handling.exceptions;

/**
 * An exception thrown when no criteria in a CollisionChart can be matched for a given collision
 */
public class NoCollisionCriteriaMatchException extends RuntimeException {
  public NoCollisionCriteriaMatchException(String message) {
    super(message);
  }
}
