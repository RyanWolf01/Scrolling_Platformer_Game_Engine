package ooga.view.levelscreenbuttons;

import javafx.event.ActionEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ooga.view.View;

import java.io.File;

public class QuitButton extends GUIBasicButton{

  public static final String SAVED_GAME_PATH = "savedgames/";
  private DirectoryChooser directoryChooser;
  private Stage stage;
  private View view;

  /**
   * Constructor
   * @param buttonText
   * @param iconString
   */
  public QuitButton(String buttonText, String iconString, View myView) {
    super(buttonText, iconString);
    view = myView;

    directoryChooser = new DirectoryChooser();
    directoryChooser.setInitialDirectory(new File(SAVED_GAME_PATH));
    this.setOnClickEvent(this::openDirectoryChooser);

  }

  /**
   * Method to open to directory chooser when the user clicks on the load button
   * @param actionEvent
   */
  private void openDirectoryChooser(ActionEvent actionEvent) {
    // TODO: need this method to perform opening with right stage (game stage)
    File levelDirectory = directoryChooser.showDialog(stage);
    System.out.println(levelDirectory);
    // LOG.debug(this.levelDirectory);
  }

  private void setView(View myView){
    view = myView;
  }
}
