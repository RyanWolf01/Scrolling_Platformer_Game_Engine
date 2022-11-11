package ooga.model.collisions.collision_handling.exceptions;

/**
 * An exception thrown when a CollisionChart cannot be found
 */
public class CollisionChartNotFoundException extends RuntimeException {
  public CollisionChartNotFoundException(String message) {
    super(message);
  }
}
