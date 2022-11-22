package ooga.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.GameController;
import ooga.view.nodes.NodeContainer;
import ooga.view.screens.LevelScreen;

import java.io.File;

public class View {

  GameController myController;
  Timeline levelAnimation;

  LevelScreen level;
  private static final double FRAME_DELAY = 1/60;

  public View(Stage mainStage, String GameTitle, File levelFile){
    //myController = new GameController(levelFile);
    level = new LevelScreen(myController);
    mainStage.setScene(level.initiateLevel(levelFile));

    levelAnimation = new Timeline();
    levelAnimation.setCycleCount(Timeline.INDEFINITE);
    levelAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(FRAME_DELAY), e -> this.step(FRAME_DELAY)));
  }

  private void step(double frameTime){
    NodeContainer nextNodes = myController.step();
    level.step(nextNodes);
  }

}
