package ooga.controller.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MiscellaneousPropertiesException extends CustomException {
  private static final Logger LOG = LogManager.getLogger(MiscellaneousPropertiesException.class);

  /**
   * Exception parsing some properties file
   * @param message to be displayed on alert
   */
  public MiscellaneousPropertiesException(String message) {
    super(message);
    LOG.error(message);
  }

  /**
   * Exception parsing some properties file
   * @param message to be displayed on alert
   * @param e exception
   */
  public MiscellaneousPropertiesException(String message, Exception e) {
    super(message, e);
    LOG.error(message, e);
  }
}
