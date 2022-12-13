package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageChooserTest extends DukeApplicationTest {

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

  @Test
  void testLanguageChooserItalian(){
    languageSelector = lookup("#Language").query();
    select(languageSelector, "Italian");
    String expected = "Benvenuto al Gioco";


    assertEquals(expected, stage.getTitle());
  }

  @Test
  void testLanguageChooserGerman(){
    languageSelector = lookup("#Language").query();
    select(languageSelector, "German");
    String expected = "Willkommen beim Spiel";


    assertEquals(expected, stage.getTitle());
  }

}
