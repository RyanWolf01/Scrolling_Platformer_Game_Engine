package ooga.model.entities.containers.exceptions;

/**
 * An exception thrown when a type cannot be found
 */
public class InvalidTypeException extends RuntimeException{

  public InvalidTypeException(String message) {
    super(message);
  }

  public InvalidTypeException(String message, Exception e) {
    super(message, e);
  }
}
