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

    public static final String DEFAULT_RESOURCE_PACKAGE = System.getProperty("user.dir") + "/data/";
    private GameController myController;
    private Timeline myAnimation;
    private static final double FRAME_DELAY = 1/60;

    /**
     * A method to test (and a joke :).
     */
    public double getVersion () {
        return 0.001;
    }


    @Override
    public void start(Stage primaryStage){
        //TODO : Find out what the path for collision should be
        myController = new GameController(Path.of(DEFAULT_RESOURCE_PACKAGE + "games/mario/levels/testLevel1.json"), Path.of("Collision Path"));
        LevelScreen testLevel = new LevelScreen(myController);
        primaryStage.setScene(testLevel.initiateLevel());
        primaryStage.show();
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(FRAME_DELAY), e -> testLevel.step(FRAME_DELAY)));
    }
}
