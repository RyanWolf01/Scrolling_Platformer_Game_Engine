package ooga.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ooga.Main;

import java.util.Map;
import java.util.ResourceBundle;

public class ExceptionAlerter {
  private ResourceBundle languages;

  public ExceptionAlerter(String language){
     languages = ResourceBundle.getBundle(Main.PROPERTIES_PACKAGE+"languages."+language);
  }

  public void displayAlert(RuntimeException exception) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(languages.getString("error_title"));
    alert.setContentText(languages.getString(exception.getMessage()));
    alert.showAndWait();
  }

}
