package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import ooga.view.interactives.GameSelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelSelectorTest extends DukeApplicationTest {

  private ComboBox<String> languageSelector;
  private Scene scene;
  private Stage stage;

  @Override
  public void start(Stage myStage){
    stage = myStage;
    StartScreen startScreen = new StartScreen(stage);
    scene = startScreen.makeScene();
    stage.setScene(scene);
    stage.show();
  }


  @BeforeEach
  void chooseEnglish(){
    languageSelector = lookup("#Language").query();
    select(languageSelector, "English");
    GameSelector gameSelector = lookup("#GameSelector").query();
    select(gameSelector, "Super Mario Bros");
  }



}
