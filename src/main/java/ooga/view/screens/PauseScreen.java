package ooga.view.screens;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import ooga.Main;
import ooga.controller.GameController;
import ooga.view.View;
import ooga.view.levelscreenbuttons.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PauseScreen {

  private final int sceneWidth = 400;
  private final int sceneHeight = 400;
  private View view;
  private GameController controller;
  private LoadButton load;
  private SaveButton save;
  private ResumeButton resume;
  private QuitButton quit;
  private List<Node> buttonList;
  private GridPane pausePane;
  private String language;
  private ResourceBundle languageResources;


  public PauseScreen(View myView, GameController myController, String myLanguage){
    view = myView;
    controller = myController;
    language = myLanguage;
    setLanguage();
  }

  private void setLanguage() {
    languageResources = ResourceBundle.getBundle(Main.PROPERTIES_PACKAGE + "languages." + language);
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
    load = new LoadButton(languageResources.getString("load"), "load_game", view);
    save = new SaveButton(languageResources.getString("save"), "save_game", view);
    resume = new ResumeButton(languageResources.getString("resume"), "resume_game", view);
    quit = new QuitButton(languageResources.getString("quit"), "quit_game", view);
    buttonList = new ArrayList<>();
    buttonList.addAll(Arrays.asList(load.getButton(), save.getButton(), resume.getButton(), quit.getButton()));
  }
}
