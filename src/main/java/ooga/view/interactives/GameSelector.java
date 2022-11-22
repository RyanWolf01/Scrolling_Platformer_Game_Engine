package ooga.view.interactives;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import ooga.view.screens.StartScreen;

public class GameSelector extends ComboBox<String> {


  private static final String[] GAME_LIST = {"Super Mario Bros", "Doodle Jump", "Geometry Dash", "Duvall Life Simulator"};

  private static final String CHOOSE_GAME_PROMPT = "Select a Game";
  private String selectedGame;

  private StartScreen myStartScreen;

  public GameSelector(StartScreen startScreen){
    super();
    myStartScreen = startScreen;
    createGamesList();
  }

  private void createGamesList(){
    //TODO: Get list of games from a json

    this.setPromptText(CHOOSE_GAME_PROMPT);
    this.getItems().addAll(GAME_LIST);
    this.setOnAction(event ->{
      selectedGame = this.getValue();
      myStartScreen.changeBackground(selectedGame + ".png");
    });

  }
}
