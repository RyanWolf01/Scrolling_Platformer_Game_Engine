package ooga.controller.exceptions;

import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class CustomException extends RuntimeException {

  private static final Logger LOG = LogManager.getLogger(CustomException.class);
  public static final String ERROR_TYPES_PATH = "properties.ErrorTypes";
  public ResourceBundle errorTypes;
  private final String extraInformation;

  /**
   * Constructor for the Custom exceptions in the program
   * @param messageKey, string message for the error
   */
  public CustomException(String messageKey) {
    this(messageKey, "");
  }

  /**
   * Constructor for the Custom exceptions in the program
   * @param messageKey, string message for the error
   * @param extraInformation, string to be appended to final message
   */
  public CustomException(String messageKey, String extraInformation) {
    super(messageKey);
    this.extraInformation = extraInformation;
    errorTypes = ResourceBundle.getBundle(ERROR_TYPES_PATH);
    LOG.error(messageKey);
  }

  /**
   * Constructor for the Custom exceptions in the program
   * @param messageKey, string message for the error
   * @param e, Exception to be passed to super
   */
  public CustomException(String messageKey, Exception e) {
    this(messageKey, "", e);
  }

  /**
   * Constructor for the Custom exceptions in the program
   * @param messageKey, string message for the error
   * @param extraInformation, string to be appended to final message
   * @param e, Exception to be passed to super
   */
  public CustomException(String messageKey, String extraInformation, Exception e) {
    super(messageKey, e);
    this.extraInformation = extraInformation;
    errorTypes = ResourceBundle.getBundle(ERROR_TYPES_PATH);
    LOG.error(messageKey);
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
  public String getErrorMessageKey() {
    return this.getMessage();
  }

  public String getExtraInformation() {
    return this.extraInformation;
  }

}
