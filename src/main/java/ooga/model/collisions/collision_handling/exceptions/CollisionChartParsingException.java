package ooga.model.collisions.collision_handling.exceptions;

/**
 * An exception thrown when a CollisionChart cannot be found
 */
public class CollisionChartParsingException extends RuntimeException {

  public CollisionChartParsingException(String message) {
    super(message);
  }

  public CollisionChartParsingException(String message, Exception e) {
    super(message, e);
  }
}
