package ooga.model.collisions.physics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntitiesNotCollidingException extends RuntimeException {
  private static final Logger LOG = LogManager.getLogger(EntitiesNotCollidingException.class);
  public static final String MESSAGE_KEY = "entities_not_colliding_exception";

  public EntitiesNotCollidingException() {
    super(MESSAGE_KEY);
    LOG.error(MESSAGE_KEY);
  }

  public EntitiesNotCollidingException(Exception e) {
    super(MESSAGE_KEY, e);
    LOG.error(MESSAGE_KEY, e);
  }
}
