package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ooga.Main;
import ooga.view.View;
import ooga.view.interactives.GameSelector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class StartScreen {

  private final int sceneWidth = 400;

  private final int sceneHeight = 400;
  public static final int MAX_USER_NAME_LENGTH = 20;
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
  private TextField nameCreator;
  private ComboBox<String> languageSelector;
  private String slash = System.getProperty("file.separator");
  private static final Logger LOG = LogManager.getLogger(StartScreen.class);
  private ResourceBundle languageResources;

  Stage mainStage;
  public StartScreen(Stage primaryStage){
    mainStage = primaryStage;
  }


  public Scene makeScene(){
    //TODO: Split into multiple methods
    gameChooser = new GridPane();
    getLevelDirectoryMap();


    languageSelector = new ComboBox<>();
    languageSelector.setPromptText("Choose a Language");
    languageSelector.getItems().addAll(View.languageResources.getString("languages").split(","));
    languageSelector.setOnAction(event -> {
      changeLanguage(languageSelector.getValue());
      languageSelector.setVisible(false);
      createSelectorButtons();
    });
    gameChooser.add(languageSelector, 0, 0);


    return new Scene(gameChooser, sceneWidth, sceneHeight);
  }

  private void createSelectorButtons(){
    startGame = new Button();
    startGame.setText(languageResources.getString("start_game"));

    startGame.setOnAction(event -> {
      new View(mainStage, gameSelector.getValue(), levelDirectory, nameCreator.getText(0, Math.min(nameCreator.getText().length(), MAX_USER_NAME_LENGTH)), languageSelector.getValue());
    });

    nameCreator = new TextField(languageResources.getString("enter_name"));
    gameChooser.add(nameCreator, 0, 0);

    gameChooser.add(startGame, 0, 3);
    startGame.setVisible(false);

    levelSelection = createLevelButton();
    gameChooser.add(levelSelection, 0, 2);
    levelSelection.setVisible(false);

    gameSelector = new GameSelector(this);
    gameChooser.add(gameSelector, 0, 1);
  }

  public void changeBackground(String url){
    gameChooser.setBackground(new Background(new BackgroundImage(new Image(
        getClass().getResourceAsStream(RESOURCE_DIRECTORY + ICON_DIRECTORY + url)), null, null, null, null)));
  }

  private Button createLevelButton(){
    Button levelButton = new Button();


    levelButton.setText(languageResources.getString("choose_level"));
    levelButton.setOnAction(e -> {
      String levelFile = chooseLevel(languageResources.getString("choose_level"));
      if(levelFile != null){
        levelButton.setText(levelFile);
        startGame.setVisible(true);
        LOG.info("Got Level Directory Successfully");
      } else {
        levelButton.setTextFill(Color.RED);
        levelButton.setText(languageResources.getString("not_valid_directory"));
        startGame.setVisible(false);
        LOG.error("Did not get Level Directory");
      }
    });

    return levelButton;
  }

  private Map getLevelDirectoryMap(){
    gameToGame = new HashMap<>();
    String[] games = {"mario", "doodle" + slash, "dash" + slash};
    for (int i = 0; i < games.length; i++) {
        gameToGame.put(GAME_LIST[i], games[i]);
    }
    return gameToGame;
  }

  private String chooseLevel(String prompt){
    String fileDirectory = System.getProperty("user.dir") + slash + "data" + slash + "games" + slash + gameToGame.get(gameSelector.getValue());
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
      createWarning(languageResources.getString("not_valid_directory"));
      directoryName = null;
    }
    return directoryName;
  }


  public void createWarning(String context){
    Alert a = new Alert(Alert.AlertType.WARNING);
    a.setContentText(context);
    a.show();
  }

  public void changeLevelSelectionButtonVisible(){
    levelSelection.setVisible(true);
  }

  private void changeLanguage(String language){
    languageResources = ResourceBundle.getBundle(Main.PROPERTIES_PACKAGE + "languages." + language);
  }

}
