package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import ooga.view.View;
import ooga.view.levelscreenbuttons.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PauseScreen {

  private final int sceneWidth = 400;
  private final int sceneHeight = 400;
  private View view;
  private LoadButton load;
  private SaveButton save;
  private ResumeButton resume;
  private QuitButton quit;
  private List<GUIBasicButton> buttonList;
  private GridPane pausePane;


  public PauseScreen(View myView){
    view = myView;
  }

  public Scene makeScene(){
    pausePane = new GridPane();
    makeButtons();
    for(int buttonCounter = 0; buttonCounter<buttonList.size(); buttonCounter++){
      pausePane.add(buttonList.get(buttonCounter), 0, buttonCounter);
    }

    return new Scene(pausePane, sceneWidth, sceneHeight);
  }


  private void makeButtons(){
    load = new LoadButton(View.viewResources.getString("load"), GUIBasicButton.ICON_PROPERTIES.getString("load_game"));
    save = new SaveButton(View.viewResources.getString("save"), GUIBasicButton.ICON_PROPERTIES.getString("save_game"));
    resume = new ResumeButton(View.viewResources.getString("resume"), GUIBasicButton.ICON_PROPERTIES.getString("resume_game"), view);
    quit = new QuitButton(View.viewResources.getString("quit"), GUIBasicButton.ICON_PROPERTIES.getString("quit_game"), view);
    buttonList = new ArrayList<>();
    buttonList.addAll(Arrays.asList(load, save, resume, quit));
  }
}
