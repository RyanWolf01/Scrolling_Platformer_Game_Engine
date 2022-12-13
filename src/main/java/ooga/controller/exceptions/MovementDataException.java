package ooga.controller.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MovementDataException extends CustomException {
  private static final Logger LOG = LogManager.getLogger(MovementDataException.class);

  /**
   * Exception parsing or changing movement data
   * @param message to be displayed on alert
   */
  public MovementDataException(String message) {
    super(message);
    LOG.error(message);
  }

  /**
   * Exception parsing or changing movement data
   * @param message to be displayed on alert
   * @param e exception
   */
  public MovementDataException(String message, Exception e) {
    super(message, e);
    LOG.error(message, e);
  }
}
