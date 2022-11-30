package ooga.model.actionparsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Exception thrown when an exception occurs while running (or as a result of running) the
 * ActionParsers.
 */
public class ActionParsingException extends RuntimeException {

  private static final Logger LOG = LogManager.getLogger(ActionParsingException.class);

  public ActionParsingException(String s) {
    super(s);
    LOG.error(s);
  }

  public ActionParsingException(String s, Exception e) {
    super(s, e);
    LOG.error(s, e);
  }
}
