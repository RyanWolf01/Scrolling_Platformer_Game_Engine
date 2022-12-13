package ooga.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ooga.controller.exceptions.CustomException;

public class ExceptionAlert {

  private Alert alert;

  public ExceptionAlert (CustomException exception) {
    alert = new Alert(AlertType.ERROR);
    alert.setTitle(exception.getErrorType());
    alert.setContentText(exception.getErrorMessageKey());
    alert.showAndWait();
  }

}
