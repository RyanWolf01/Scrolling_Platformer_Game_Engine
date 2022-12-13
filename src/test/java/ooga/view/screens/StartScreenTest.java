package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartScreenTest extends DukeApplicationTest {

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


  @Test
  void testLanguageChooserEnglish(){
    languageSelector = lookup("#Language").query();
    select(languageSelector, "English");
    String expected = "Welcome to the Game";


    assertEquals(expected, stage.getTitle());
  }

  @Test
  void testLanguageChooserSpanish(){
    languageSelector = lookup("#Language").query();
    select(languageSelector, "Spanish");
    String expected = "Bienvenido al juego";


    assertEquals(expected, stage.getTitle());
  }

}
