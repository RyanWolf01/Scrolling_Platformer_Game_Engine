package ooga.view.levelscreenbuttons;

import javafx.event.ActionEvent;
import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class LoadButton extends GUIBasicButton {
  public static final String SAVED_GAME_PATH = "savedgames/";
  private DirectoryChooser directoryChooser;
  private Stage stage;

  /**
   * Constructor
   * @param buttonText
   * @param iconString
   */
  public LoadButton(String buttonText, String iconString) {
    super(buttonText, iconString);

    directoryChooser = new DirectoryChooser();
    directoryChooser.setInitialDirectory(new File(SAVED_GAME_PATH));
    this.setOnClickEvent(this::openDirectoryChooser);

  }

  /**
   * Method to open to directory chooser when the user clicks on the load button
   * @param e
   */
  private void openDirectoryChooser(ActionEvent e) {
    // TODO: need this method to perform opening with right stage (game stage)
    File levelDirectory = directoryChooser.showDialog(stage);
    // System.out.println(levelDirectory);
    // LOG.debug(this.levelDirectory);
  }
}
