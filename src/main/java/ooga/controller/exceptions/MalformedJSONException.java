package ooga.controller.exceptions;

import ooga.model.collisions.collisionhandling.exceptions.CollisionChartParsingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MalformedJSONException extends RuntimeException{
    private static final Logger LOG = LogManager.getLogger(MalformedJSONException.class);


    public MalformedJSONException(String message) {
        super(message);
        LOG.error(message);
    }

    public MalformedJSONException(String message, Exception e) {
        super(message, e);
        LOG.error(message, e);
    }
}
