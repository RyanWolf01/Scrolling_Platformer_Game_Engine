package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ooga.view.View;
import ooga.view.interactives.GameSelector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class StartScreen {

  private static HashMap<String, String> gameToGame;
  private static final String RESOURCE_DIRECTORY = "/";
  private static final String ICON_DIRECTORY = "icongames/";
  private static final String[] GAME_LIST = {"Super Mario Bros", "Doodle Jump", "Geometry Dash"};
  private static final String NO_GAME_DIRECTORY = "Cannot Find Initial Game Directory";
  private File levelDirectory;
  private DirectoryChooser directoryChooser;
  private GridPane gameChooser;
  private GameSelector gameSelector;
  private Button startGame;
  private Button levelSelection;
  private static final Logger LOG = LogManager.getLogger(StartScreen.class);

  Stage mainStage;
  public StartScreen(Stage primaryStage){
    mainStage = primaryStage;
    mainStage.setScene(makeScene());
    mainStage.show();
  }


  private Scene makeScene(){
    gameChooser = new GridPane();
    getLevelDirectoryMap();

    startGame = new Button();
    startGame.setText("Start Game");

    startGame.setOnAction(event -> {
      new View(mainStage, gameSelector.getValue(), levelDirectory);
    });
    gameChooser.add(startGame, 0, 2);
    startGame.setVisible(false);

    levelSelection = createLevelButton();
    gameChooser.add(levelSelection, 0, 1);
    levelSelection.setVisible(false);

    gameSelector = new GameSelector(this);
    gameChooser.add(gameSelector, 0, 0);


    return new Scene(gameChooser, 400, 400);
  }

  public void changeBackground(String url){
    gameChooser.setBackground(new Background(new BackgroundImage(new Image(
        getClass().getResourceAsStream(RESOURCE_DIRECTORY + ICON_DIRECTORY + url)), null, null, null, null)));
  }

  private Button createLevelButton(){
    Button levelButton = new Button();


    levelButton.setText("Choose Level");
    levelButton.setOnAction(e -> {
      String levelFile = chooseLevel("Choose a Level Directory");
      if(levelFile != null){
        levelButton.setText(levelFile);
        startGame.setVisible(true);
        LOG.info("Got Level Directory Successfully");
      } else {
        levelButton.setTextFill(Color.RED);
        levelButton.setText("Not a Valid Level Directory");
        startGame.setVisible(false);
        LOG.error("Did not get Level Directory");
      }
    });

    return levelButton;
  }

  private Map getLevelDirectoryMap(){
    gameToGame = new HashMap<>();
    String[] games = {"mario", "doodle" + System.getProperty("file.separator"), "dash" + System.getProperty("file.separator")};
    for (int i = 0; i < games.length; i++) {
        gameToGame.put(GAME_LIST[i], games[i]);
    }
    return gameToGame;
  }

  private String chooseLevel(String prompt){
    String fileDirectory = System.getProperty("user.dir") + System.getProperty("file.separator") + "data" + System.getProperty("file.separator")+ "games" + System.getProperty("file.separator") + gameToGame.get(gameSelector.getValue());
    LOG.debug(fileDirectory);
    directoryChooser = new DirectoryChooser();
    try{
      directoryChooser.setInitialDirectory(new File(fileDirectory));
      LOG.debug("Found the Initial Directory Successfully");
    }
    catch(Exception e){
      directoryChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
      LOG.error("Did not find Initial Directory Successfully.");
      createWarning(NO_GAME_DIRECTORY);
    }
    directoryChooser.setInitialDirectory(new File(fileDirectory));
    directoryChooser.getInitialDirectory();
    directoryChooser.setTitle(prompt);
    levelDirectory = directoryChooser.showDialog(mainStage);
    System.out.println(levelDirectory);
    LOG.debug(this.levelDirectory);
    String directoryName;
    try{
      directoryName = levelDirectory.getName();
    } catch (NullPointerException e){
      createWarning("Please Choose a Valid Level Directory");
      directoryName = null;
    }
    return directoryName;
  }


  public void createWarning(String context){
    Alert a = new Alert(Alert.AlertType.WARNING);
    a.setContentText(context);
    a.show();
  }

  public void changeLevelSelectionButtonVisibile(){
    levelSelection.setVisible(true);
  }

}
