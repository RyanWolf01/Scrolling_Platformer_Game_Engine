package ooga.view.levelscreenbuttons;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ooga.controller.GameController;
import ooga.view.View;

public class SaveButton extends GUIBasicButton {

  private Stage stage;
  public TextField directoryNameInput;
  private GameController myController;


  public SaveButton(String buttonText, String iconString, View myView) {
    super(buttonText, iconString, myView);
    this.setOnClickEvent(this::openTextPopup);
  }

  /**
   * Method to open a text popup for the user to input their name for the
   * save directory in the saved games section of the program
   * @param actionEvent
   */
  private void openTextPopup(ActionEvent actionEvent) {
    directoryNameInput = new TextField();
    directoryNameInput.setOnAction(event ->
        handleTextInput(directoryNameInput.getText()));
    // TODO: switch out string to be read from props
    directoryNameInput.setPromptText("Enter Name for Saved File");

  }

  /**
   * Method that takes in string inputted by user and creates the saved directory from it
   *
   * @param saveName
   */
  private void handleTextInput(String saveName) {
    //myView.saveGame(saveName);
  }

}
