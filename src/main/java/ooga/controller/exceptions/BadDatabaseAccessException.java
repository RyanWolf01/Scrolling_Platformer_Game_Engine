package ooga.controller.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BadDatabaseAccessException extends RuntimeException{
    private static final Logger LOG = LogManager.getLogger(BadDatabaseAccessException.class);


    public BadDatabaseAccessException(String message) {
        super(message);
        LOG.error(message);
    }

    public BadDatabaseAccessException(String message, Exception e) {
        super(message, e);
        LOG.error(message, e);
    }
}
