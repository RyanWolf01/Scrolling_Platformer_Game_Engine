package ooga.controller.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CollisionChartCreationException extends CustomException {
  private static final Logger LOG = LogManager.getLogger(CollisionChartCreationException.class);

  /**
   * Exception parsing some properties file
   * @param message to be displayed on alert
   */
  public CollisionChartCreationException(String message) {
    super(message);
    LOG.error(message);
  }

  /**
   * Exception parsing some properties file
   * @param message to be displayed on alert
   * @param e exception
   */
  public CollisionChartCreationException(String message, Exception e) {
    super(message, e);
    LOG.error(message, e);
  }
}
