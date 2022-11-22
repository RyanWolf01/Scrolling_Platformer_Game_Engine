package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ooga.view.View;
import ooga.view.interactives.GameSelector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.HashMap;

public class StartScreen {

  private static HashMap<String, String> gameToGame;
  private static final String RESOURCE_DIRECTORY = "/";
  private static final String ICON_DIRECTORY = "icongames/";
  private static final String GAMES_DIRECTORY = "games/";
  private static final String LEVEL_DIRECTORY = "levels/";
  private static final String[] GAME_LIST = {"Super Mario Bros", "Doodle Jump", "Geometry Dash", "Duvall Life Simulator"};
  private File levelDirectory;
  private DirectoryChooser directoryChooser;
  private GridPane gameChooser;
  private GameSelector gameSelector;
  private static final Logger LOG = LogManager.getLogger(StartScreen.class);

  Stage mainStage;
  public StartScreen(Stage primaryStage){
    mainStage = primaryStage;
    mainStage.setScene(makeScene());
    mainStage.show();
  }


  private Scene makeScene(){
    gameChooser = new GridPane();

    gameSelector = new GameSelector(this);
    gameChooser.add(gameSelector, 0, 0);

    createLevelDirectoryMap();
    Button levelSelection = createLevelButton();
    gameChooser.add(levelSelection, 0, 1);

    Button startGame = new Button();
    startGame.setText("Start Game");

    startGame.setOnAction(event -> {
      new View(mainStage, gameSelector.getValue(), levelDirectory);
    });
    gameChooser.add(startGame, 0, 2);


    return new Scene(gameChooser, 400, 400);
  }

  public void changeBackground(String url){
    gameChooser.setBackground(new Background(new BackgroundImage(new Image(RESOURCE_DIRECTORY + ICON_DIRECTORY + url), null, null, null, null)));
  }

  private Button createLevelButton(){
    Button levelButton = new Button();


    levelButton.setText("Choose Level");
    levelButton.setOnAction(event -> chooseLevel());


    return levelButton;
  }

  private void createLevelDirectoryMap(){
    gameToGame = new HashMap<>();
    String[] games = {"mario/", "doodle/", "dash/"};
    for (int i = 0; i < games.length; i++) {
        gameToGame.put(GAME_LIST[i], games[i]);
    }
  }

  private void chooseLevel(){
    FileChooser fileChooser = new FileChooser();
    String fileDirectory = System.getProperty("user.dir");
    //String levelDirectory = RESOURCE_DIRECTORY + GAMES_DIRECTORY + gameToGame.get(gameSelector.getValue()) + LEVEL_DIRECTORY;
    directoryChooser = new DirectoryChooser();
    directoryChooser.setInitialDirectory(new File(fileDirectory));
    levelDirectory = directoryChooser.showDialog(mainStage);
    System.out.println(levelDirectory);
    LOG.debug(this.levelDirectory);
  }

}
