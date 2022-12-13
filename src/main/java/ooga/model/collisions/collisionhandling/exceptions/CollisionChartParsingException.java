package ooga.model.collisions.collisionhandling.exceptions;

import ooga.controller.exceptions.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An exception thrown when a CollisionChart cannot be found
 */
public class CollisionChartParsingException extends CustomException {
  private static final Logger LOG = LogManager.getLogger(CollisionChartParsingException.class);
  private static final String MESSAGE_KEY = "collision_chart_parsing_exception";
  private static final String MESSAGE_KEY2 = "collision_chart_parsing_exception2";

  public CollisionChartParsingException() {
    super(MESSAGE_KEY2);
    LOG.error(MESSAGE_KEY2);
  }

  public CollisionChartParsingException(String extraInformation, Exception e) {
    super(MESSAGE_KEY, extraInformation, e);
    LOG.error(MESSAGE_KEY + " " + extraInformation, e);
  }
}
