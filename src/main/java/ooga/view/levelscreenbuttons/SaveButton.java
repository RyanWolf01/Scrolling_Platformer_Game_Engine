package ooga.view.levelscreenbuttons;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SaveButton extends GUIBasicButton {

  private Stage stage;

  private TextField directoryNameInput;


  public SaveButton(String buttonText, String iconString) {
    super(buttonText, iconString);
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
    /*
    createSavedDirectory(saveName)
    MAKE THIS METHOD WHEREVER IS MOST SENSIBLE IN THE CONTROLLER TO USE THE
    BACKEND CONTAINER TO MAKE THE NEW JSON AND PASS THE OTHER JSON OBJECTS
    TO THE DIRECTORY
     */
  }

}
