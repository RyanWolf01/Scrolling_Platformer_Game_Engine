package ooga.model.actionparsers;

/**
 * Exception thrown when an exception occurs while running (or as a result of running) the
 * ActionParsers.
 */
public class ActionParsingException extends RuntimeException {

  public ActionParsingException(String s) {
    super(s);
  }

  public ActionParsingException(String s, Exception e) {
    super(s, e);
  }
}
