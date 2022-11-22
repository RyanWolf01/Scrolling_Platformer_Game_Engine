package ooga;


import javafx.application.Application;
import javafx.stage.Stage;
import ooga.view.screens.StartScreen;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {
  public static final String COLLISION_CHART_PATH = System.getProperty("user.dir") + "data/games/sprint_1_test/collisions.json";
  public static final String DEFAULT_RESOURCE_PACKAGE = System.getProperty("user.dir") + "/src/main/resources/";


  /**
   * A method to test (and a joke :).
   */
  public double getVersion() {
    return 0.001;
  }


  @Override
  public void start(Stage primaryStage) {
    //Create Splash Screen and set it to the stage
    StartScreen startScreen = new StartScreen(primaryStage);
  }
}
