package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ooga.view.View;
import ooga.view.interactives.GameSelector;

import java.io.File;
import java.util.HashMap;

public class StartScreen {

  private static HashMap<String, String> gameToGame;
  private static final String RESOURCE_DIRECTORY = System.getProperty("user.dir") + "\\data\\";
  private static final String ICON_DIRECTORY =  "icons\\";
  private static final String GAMES_DIRECTORY = "games\\";
  private static final String LEVEL_DIRECTORY = "levels\\";
  private static final String[] GAME_LIST = {"Super Mario Bros", "Doodle Jump", "Geometry Dash", "Duvall Life Simulator"};
  private File levelFile;

  private GridPane gameChooser;
  private GameSelector gameSelector;

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
      new View(mainStage, gameToGame.get(gameSelector.getValue()), levelFile);
    });
    gameChooser.add(startGame, 0, 2);


    return new Scene(gameChooser, 400, 400);
  }

  public void changeBackground(String url){
    gameChooser.setBackground(new Background(new BackgroundImage(new Image(RESOURCE_DIRECTORY + ICON_DIRECTORY + url), null, null, null, null)));
  }

  private Button createLevelButton(){
    Button levelButton = new Button();
    FileChooser fileChooser = new FileChooser();

    levelButton.setText("Choose Level");
    levelButton.setOnAction(event -> {
      String gameDirectory = RESOURCE_DIRECTORY + GAMES_DIRECTORY + gameToGame.get(gameSelector.getValue()) + LEVEL_DIRECTORY;
      // logger.info("PathName of Level" + RESOURCE_DIRECTORY + LEVEL_DIRECTORY + gameDirectory);
      System.out.println(gameDirectory);
      fileChooser.setInitialDirectory(new File(gameDirectory));
      levelFile = fileChooser.showOpenDialog(mainStage);
    });


    return levelButton;
  }

  private void createLevelDirectoryMap(){
    gameToGame = new HashMap<>();
    String[] games = {"mario\\", "doodle\\", "dash\\"};
    for (int i = 0; i < games.length; i++) {
        gameToGame.put(GAME_LIST[i], games[i]);
    }
  }

}
