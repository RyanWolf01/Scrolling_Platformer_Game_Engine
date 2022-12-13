package ooga.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.Main;
import ooga.controller.GameController;
import ooga.view.nodes.NodeContainer;
import ooga.view.screens.EndScreen;
import ooga.view.screens.LevelScreen;
import ooga.view.screens.PauseScreen;
import ooga.view.screens.WaitingScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ResourceBundle;

public class View {

  private GameController myController;
  private String playerName;
  private static Timeline levelAnimation;
  private LevelScreen level;
  private static final double FRAME_DELAY = 1.0/60.0;
  private Stage myStage;
  public static final ResourceBundle viewResources = ResourceBundle.getBundle(Main.PROPERTIES_PACKAGE+"View");
  private ResourceBundle languageResources;
  private static final Logger LOG = LogManager.getLogger(View.class);
  private String language;
  private String gameTitle;
  private String name;
  private ExceptionAlerter alerter;

  public View(Stage mainStage, String GameTitle, File levelDirectory, String name, String myLanguage){
    myStage = mainStage;
    language = myLanguage;
    languageResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_RESOURCE_PACKAGE+language);
    alerter = new ExceptionAlerter(language);
    playerName = name;

    try {
      myController = new GameController(levelDirectory + "/level.json", levelDirectory + "/collisions.json", levelDirectory + "/controls.json");
      level = new LevelScreen(myController, this);
    } catch (RuntimeException e){
      alerter.displayAlert(e);
    }
    myStage.setScene(level.makeScene(new File(levelDirectory + "/level.json")));
    myStage.setTitle(GameTitle);

    levelAnimation = new Timeline();
    levelAnimation.setCycleCount(Timeline.INDEFINITE);
    levelAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(FRAME_DELAY), e -> this.step(FRAME_DELAY)));
    levelAnimation.play();
    myStage.getScene().setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    gameTitle = GameTitle;
  }

  private void step(double frameTime){
    NodeContainer nextNodes = null;
    try {
      nextNodes = myController.step();
    } catch(RuntimeException e){
      alerter.displayAlert(e);
    }

    level.step(nextNodes);
    level.setScore(myController.getPlayerScore());
    level.setLiveCount(myController.getMainCharacterLives());
  }

  public void finishLevel(){
    levelAnimation.stop();
    WaitingScreen waitingScreen = new WaitingScreen();
    myStage.setScene(waitingScreen.makeScene());
    myController.setHighScore(myController.getPlayerScore());
    for(int score: myController.getHighScores().keySet()){
      LOG.info(score);
    }
    EndScreen endScreen = new EndScreen(language, myController.getHighScores());
    myStage.setScene(endScreen.makeScene());
    myStage.setTitle(languageResources.getString("game_over"));
  }


/**
* Handles Key Codes that are input by the user. Intercepts the view-oriented controls like pausing, but sends all others to controller
 * @param code
*/
  private void handleKeyInput(KeyCode code){
    //TODO: Use a JSON in the controller that states the "view-oriented" controls instead of an if tree
    if(code == KeyCode.P){
      pause();
    } else if(code == KeyCode.O){
      finishLevel();
    }
    else {
      myController.handleKeyInput(code);
    }
  }

  private void pause(){
    LOG.info(languageResources.getString("pause_game"));
    levelAnimation.pause();
    PauseScreen pause = new PauseScreen(this, myController, language);
    myStage.setScene(pause.makeScene());
  }

  public void play(){
    LOG.info(languageResources.getString("play_game"));
    myStage.setScene(level.getScene());
    levelAnimation.play();
  }

  public Stage getMyStage(){
    return myStage;
  }

  public void saveGame(String directoryName){
    myController.saveGame(directoryName);
  }

  public String getLanguage() {
    return language;
  }

  public String getName() {
    return playerName;
  }

  public String getGameTitle() {
    return gameTitle;
  }
}
