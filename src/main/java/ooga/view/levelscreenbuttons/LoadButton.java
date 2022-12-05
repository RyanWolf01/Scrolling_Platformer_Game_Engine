package ooga.view.levelscreenbuttons;

import java.io.File;
import javafx.stage.DirectoryChooser;

public class LoadButton extends GUIBasicButton {

  public static final String SAVED_GAME_PATH = "savedgames/";
  private DirectoryChooser directoryChooser;
  public LoadButton(String buttonText, String iconString) {
    super(buttonText, iconString);

    directoryChooser = new DirectoryChooser();
    directoryChooser.setInitialDirectory(new File(SAVED_GAME_PATH));
    // TODO: need this method to
    // File levelDirectory = directoryChooser.showDialog(NEED RIGHT STAGE HERE);
    // System.out.println(levelDirectory);
    // LOG.debug(this.levelDirectory);
  }
}
