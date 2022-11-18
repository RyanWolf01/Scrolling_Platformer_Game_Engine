package ooga.model.actionparsers;

public class ActionParsingException extends RuntimeException {

  public ActionParsingException(String s) {
    super(s);
  }

  public ActionParsingException(String s, Exception e) {
    super(s, e);
  }
}
