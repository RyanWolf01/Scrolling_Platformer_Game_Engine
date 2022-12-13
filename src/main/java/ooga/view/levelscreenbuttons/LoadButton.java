package ooga.view.levelscreenbuttons;

import java.util.logging.LogManager;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ooga.controller.GameController;
import ooga.view.View;

public class LoadButton extends GUIBasicButton {
  public static final String SLASH = System.getProperty("file.separator");
  public static final String SAVED_GAME_PATH = System.getProperty("user.dir") + SLASH + "src" + SLASH + "main" + SLASH
      + "resources" + SLASH + "savedgames";

  private DirectoryChooser directoryChooser;
  private Stage stage;


  /**
   * Constructor
   * @param buttonText
   * @param iconString
   * @param myView
   */
  public LoadButton(String buttonText, String iconString, View myView) {
    super(buttonText, iconString, myView);

    directoryChooser = new DirectoryChooser();
    directoryChooser.setInitialDirectory(new File(SAVED_GAME_PATH));
    this.setOnClickEvent(this::openDirectoryChooser);

  }

  /**
   * Method to open to directory chooser when the user clicks on the load button
   * @param actionEvent
   */
  private void openDirectoryChooser(ActionEvent actionEvent) {
    File levelDirectory = directoryChooser.showDialog(myView.getMyStage());
    System.out.println(levelDirectory);
    // LOG.debug(this.levelDirectory);
    String directoryName;
    try {
      directoryName = levelDirectory.getName();
      new View(myView.getMyStage(), myView.getGameTitle(), levelDirectory, myView.getName(),
          myView.getLanguage());
    } catch (NullPointerException e) {
      directoryName = null;
    }
  }
}