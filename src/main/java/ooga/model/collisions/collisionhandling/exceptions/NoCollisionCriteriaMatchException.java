package ooga.model.collisions.collisionhandling.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An exception thrown when no criteria in a CollisionChart can be matched for a given collision
 */
public class NoCollisionCriteriaMatchException extends RuntimeException {
  private static final Logger LOG = LogManager.getLogger(NoCollisionCriteriaMatchException.class);
  public static final String MESSAGE_KEY = "no_collision_criteria_match_exception";

  public NoCollisionCriteriaMatchException(String message) {
    super(MESSAGE_KEY);
    LOG.error(message);
  }
}
