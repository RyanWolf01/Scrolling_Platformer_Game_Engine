package ooga;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.GameController;
import ooga.view.screens.LevelScreen;
import ooga.view.screens.StartScreen;

import java.nio.file.Path;
import java.sql.Time;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {

  public static final String DEFAULT_RESOURCE_PACKAGE = System.getProperty("user.dir") + "\\data\\";
  public static final String COLLISION_CHART_PATH =
      System.getProperty("user.dir") + "/data/games/mario/collisionChart.json";

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
