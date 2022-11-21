package ooga.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.GameController;
import ooga.model.entities.characters.maincharacters.Mario;
import ooga.view.nodes.NodeContainer;
import ooga.view.screens.LevelScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class View {

  private GameController myController;
  private static Timeline levelAnimation;

  private LevelScreen level;
  private static final double FRAME_DELAY = 1.0/60.0;

  private static final Logger LOG = LogManager.getLogger(View.class);

  public View(Stage mainStage, String GameTitle, File levelFile){
    //myController = new GameController(levelFile);
    level = new LevelScreen(myController);
    LOG.info(levelFile);
    mainStage.setScene(level.initiateLevel(levelFile));
    mainStage.setTitle(GameTitle);

    levelAnimation = new Timeline();
    levelAnimation.setCycleCount(Timeline.INDEFINITE);
    levelAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(FRAME_DELAY), e -> this.step(FRAME_DELAY)));
    levelAnimation.play();
  }

  private void step(double frameTime){
    System.out.println("EEEE");
    NodeContainer nextNodes = myController.step();
    level.step(nextNodes);
  }

}
