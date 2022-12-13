package ooga.model.actionparsers;

import java.util.ResourceBundle;
import ooga.controller.exceptions.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Exception thrown when an exception occurs while running (or as a result of running) the
 * ActionParsers.
 */
public class ActionParsingException extends CustomException {
  
  private static final Logger LOG = LogManager.getLogger(ActionParsingException.class);
  private static final String MESSAGE = ResourceBundle.getBundle("properties/languages/English").getString("action_parsing_exception_message");

  public ActionParsingException(String s) {
    super(s);
    LOG.error(s);
  }

  public ActionParsingException(String actionDataString, Exception e) {
    super(String.format(MESSAGE, actionDataString), e);
    LOG.error(actionDataString, e);
  }
}
