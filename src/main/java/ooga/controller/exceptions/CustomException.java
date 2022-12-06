package ooga.controller.exceptions;

import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class CustomException extends RuntimeException {

  private static final Logger LOG = LogManager.getLogger(CustomException.class);
  public static final String ERROR_TYPES_PATH = "properties.ErrorTypes";
  public ResourceBundle errorTypes;

  /**
   * Constructor for the Custom exceptions in the program
   * @param message, string message for the error
   */
  public CustomException(String message) {
    super(message);
    errorTypes = ResourceBundle.getBundle(ERROR_TYPES_PATH);
    LOG.error(message);
  }

  /**
   * Method to return the type of error based on the properties file errorTypes created in the
   * constructor of the exception and using the classname as the key
   * @return string representing the error type (name of class representative of this)
   */
  public String getErrorType() {
    return this.errorTypes.getString(this.getClass().getSimpleName());
  }

  /**
   * Method to return the message of the error for the specific error as per the constructor
   * @return error message to be displayed in alert
   */
  public String getErrorMessage() {
    return this.getMessage();
  }

}
