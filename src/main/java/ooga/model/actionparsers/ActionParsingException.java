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
  private static final String MESSAGE_KEY = "action_parsing_exception_message";
  private static final String MESSAGE2_KEY = "action_parsing_exception_message2";

  public ActionParsingException(String actionDataString) {
    super(MESSAGE2_KEY);
    LOG.error(MESSAGE2_KEY + " " + actionDataString);
  }

  public ActionParsingException(String actionDataString, Exception e) {
    super(MESSAGE_KEY, actionDataString, e);
    LOG.error(MESSAGE_KEY + " " + actionDataString, e);
  }
}
