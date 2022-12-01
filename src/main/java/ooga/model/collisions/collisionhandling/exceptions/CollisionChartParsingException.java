package ooga.model.collisions.collisionhandling.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An exception thrown when a CollisionChart cannot be found
 */
public class CollisionChartParsingException extends RuntimeException {
  private static final Logger LOG = LogManager.getLogger(CollisionChartParsingException.class);


  public CollisionChartParsingException(String message) {
    super(message);
    LOG.error(message);
  }

  public CollisionChartParsingException(String message, Exception e) {
    super(message, e);
    LOG.error(message, e);
  }
}
