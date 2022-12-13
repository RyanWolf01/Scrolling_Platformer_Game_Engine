package ooga.controller.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BadImageException extends CustomException{
    private static final Logger LOG = LogManager.getLogger(BadImageException.class);

    public BadImageException(String messageKey) {
        super(messageKey);
        LOG.error(messageKey);
    }

    public BadImageException(String messageKey, Exception e){
        super(messageKey, e);
        LOG.error(messageKey, e);
    }
}
