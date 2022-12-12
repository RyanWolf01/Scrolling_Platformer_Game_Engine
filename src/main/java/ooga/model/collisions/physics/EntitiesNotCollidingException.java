package ooga.model.collisions.physics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntitiesNotCollidingException extends RuntimeException {
  private static final Logger LOG = LogManager.getLogger(EntitiesNotCollidingException.class);

  public EntitiesNotCollidingException(String s) {
    super(s);
    LOG.error(s);
  }

  public EntitiesNotCollidingException(String s, Exception e) {
    super(s, e);
    LOG.error(s, e);
  }
}
