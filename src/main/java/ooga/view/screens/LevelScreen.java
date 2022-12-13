package ooga.view.screens;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import ooga.controller.GameController;
import ooga.view.GameCamera;
import ooga.view.Margin;
import ooga.view.View;
import ooga.view.nodes.GUIElement;
import ooga.view.nodes.NodeContainer;
import ooga.view.nodes.ScrollingNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelScreen {



  private final int levelWidth = 1200;

  private final int levelHeight = 600;
  private final String BACKGROUND_DIRECTORY = "/backgrounds/";
  private Pane levelPane;
  private StackPane screenPane;
  private ScrollingNode mainCharacter;
  private NodeContainer myNodes;
  private GameController myController;
  private GameCamera myGameCamera;
  private Scene levelScene;
  private View view;
  Map<String, GUIElement> guiMap;
  private static final String[] GUI_NAMES = {"Score", "Lives"};
  private static final Logger LOG = LogManager.getLogger(LevelScreen.class);

/**
 * Creates a level based on only it's LevelName. Currently, this method is for testing a level by just calling it and creating its initial entities
 *
 * @param controller
 * @param myView
 */
  public LevelScreen(GameController controller, View myView){
    myController = controller;
    view = myView;
  }

  public Scene makeScene(File levelFile){
    screenPane = new StackPane();
    levelPane = new Pane();
    screenPane.getChildren().add(levelPane);

    levelPane.setId("Pane");
    try {
      setBackground(levelFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }


    levelScene = new Scene(screenPane, levelWidth, levelHeight);
    levelScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    createCamera();
    addGUIElements();
    return levelScene;
  }

/**
* Generates the new frame of the level
 * @param nextNodes is the list of nodes that the level needs to display for the frame
*/

  public void step(NodeContainer nextNodes){
    if(myNodes != null){
      for (Node a : myNodes) {
          levelPane.getChildren().remove(a);
      }
    }
    myNodes = nextNodes;
    mainCharacter = myNodes.getMainCharacter();
    if(mainCharacter == null){
      LOG.info("Character Has Died");
      view.finishLevel();
      return;
    }
    updateCamera(mainCharacter);
    translateNodes();
    for (Node node : myNodes) {
        levelPane.getChildren().add(node);
    }

    myNodes = new NodeContainer(nextNodes);
  }


/**
* TODO: Get the values for the margin and the start x and y from a json file
 * This creates the camera with initial values
*/
  private void createCamera(){
    double startX = 0;
    double startY = 0;
    Margin startingMargin = new Margin(100, 400, 100, 100);
    myGameCamera = new GameCamera(startX, startY, startingMargin, levelHeight, levelWidth);
    myGameCamera.setPlayerLocation(100, 0);
  }
/**
* Sends the mainCharacter to the camera, so the camera can decide if it needs to scroll
 * @param mainCharacter is the node of the mainCharacter that the camera bases its position on
*/
  private void updateCamera(ScrollingNode mainCharacter){
    //get player location from myNodes
    double playerX = mainCharacter.getBackX();
    double playerY = mainCharacter.getBackY();
    myGameCamera.setPlayerLocation(playerX, playerY);
  }

/**
* Once the new camera position is determined, it tells the NodeContainer to move them all to reflect the new camera position
*/
  private void translateNodes(){
    myNodes.updateCameraPosition(myGameCamera.getCameraX(), myGameCamera.getCameraY());
  }

/**
* Looks inside the level file, finds the background image, and sets it to the level pane
 * @param levelFile
 * @throws IOException Will be handled later
 * @throws ParseException Will be handled later
*/
  private void setBackground(File levelFile) throws IOException, ParseException {
    Background newBackground;
    FileReader infoFile = new FileReader(levelFile);
    JSONObject levelJson = (JSONObject) new JSONParser().parse(infoFile);
    String backgroundFile = (String) levelJson.get("background");
    LOG.info("Background Image Loaded: " + backgroundFile);
    Image backgroundImage = new Image(BACKGROUND_DIRECTORY + backgroundFile);

    newBackground = new Background(new BackgroundImage(backgroundImage, null, null, null, null));

    levelPane.setBackground(newBackground);
  }


  private void handleKeyInput(KeyCode code){
    if(code == KeyCode.P){
      pause();
    }
    else {
    myController.handleKeyInput(code);
    }
  }


  private void pause(){
    LOG.info("Pause Game");

    //Pause all stepping and animations
    //Display Resume, Save, Load, and Quit Buttons
    //On Resume,
  }

  public Scene getScene(){
    return levelScene;
  }

  public void setScore(int score){
    guiMap.get("Score").updateValue(score);
  }

  public void setLiveCount(int lives){
    guiMap.get("Lives").updateValue(lives);
  }

  private void addGUIElements(){
    AnchorPane anchorPane = new AnchorPane();
    anchorPane.setMinWidth(levelWidth);
    guiMap = new HashMap<>();
    for (String name : GUI_NAMES) {
      guiMap.put(name, new GUIElement(name, 0));
      anchorPane.getChildren().add(guiMap.get(name));
    }
    AnchorPane.setLeftAnchor(guiMap.get("Lives"), 0.0);
    AnchorPane.setRightAnchor(guiMap.get("Score"), 0.0);
    screenPane.getChildren().add(anchorPane);
  }
}
