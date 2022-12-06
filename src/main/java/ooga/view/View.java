package ooga.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.Main;
import ooga.controller.GameController;
import ooga.view.nodes.NodeContainer;
import ooga.view.screens.EndScreen;
import ooga.view.screens.LevelScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class View {

  private GameController myController;
  private static Timeline levelAnimation;

  private LevelScreen level;
  private static final double FRAME_DELAY = 1.0/60.0;
  private Stage myStage;

  private static final Logger LOG = LogManager.getLogger(View.class);

  public View(Stage mainStage, String GameTitle, File levelDirectory){
    myStage = mainStage;
    myController = new GameController(levelDirectory + "/level.json", levelDirectory + "/collisions.json", levelDirectory + "/controls.json");
    level = new LevelScreen(myController);
    myStage.setScene(level.initiateLevel(new File(levelDirectory + "/level.json")));
    myStage.setTitle(GameTitle);

    levelAnimation = new Timeline();
    levelAnimation.setCycleCount(Timeline.INDEFINITE);
    levelAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(FRAME_DELAY), e -> this.step(FRAME_DELAY)));
    levelAnimation.play();
  }

  private void step(double frameTime){
    NodeContainer nextNodes = myController.step();
    level.step(nextNodes);
  }

  public void finishLevel(){
    levelAnimation.stop();
    EndScreen endScreen = new EndScreen();
    myStage.setScene(endScreen.initiateScene(new File(myController.getLevelDirectory() + Main.slash + "scores.json")));
    myStage.setTitle("You Won!");
  }

}
