package ooga.view.levelscreenbuttons;

import javafx.event.ActionEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ooga.Main;
import ooga.view.View;

import java.io.File;

public class TitleScreenButton extends GUIBasicButton{

  public static final String SAVED_GAME_PATH = "savedgames/";
  private DirectoryChooser directoryChooser;
  private Stage stage;
  private View view;

  /**
   * Constructor
   * @param buttonText
   * @param iconString
   */
  public TitleScreenButton(String buttonText, String iconString, View myView) {
    super(buttonText, iconString, myView);
    view = myView;

    directoryChooser = new DirectoryChooser();
    directoryChooser.setInitialDirectory(new File(SAVED_GAME_PATH));
    this.setOnClickEvent(this::quitGame);

  }

  /**
   * Method to open to directory chooser when the user clicks on the load button
   * @param actionEvent
   */
  private void quitGame(ActionEvent actionEvent) {
    Main main = new Main();
    main.start(view.getMyStage());
  }

}
