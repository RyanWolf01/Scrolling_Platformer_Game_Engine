package ooga.view.screens;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import ooga.Main;
import ooga.controller.GameController;
import ooga.view.GameCamera;
import ooga.view.Margin;
import ooga.view.nodes.NodeContainer;
import ooga.view.nodes.ScrollingNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class LevelScreen {



  private final int levelWidth = 1200;

  private final int levelHeight = 600;
  private final String BACKGROUND_DIRECTORY = "/backgrounds/";
  private Pane levelPane;
  private ScrollingNode mainCharacter;
  private NodeContainer myNodes;
  private GameController myController;
  private GameCamera myGameCamera;
  private static final Logger LOG = LogManager.getLogger(LevelScreen.class);

/**
* Creates a level based on only it's LevelName. Currently, this method is for testing a level by just calling it and creating its initial entities
 * @param controller
*/
  public LevelScreen(GameController controller){
    myController = controller;
  }

  public Scene initiateLevel(File levelFile){
    levelPane = new Pane();

    levelPane.setId("Pane");
    try {
      setBackground(levelFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }


    Scene scene = new Scene(levelPane, levelWidth, levelHeight);
    scene.setOnKeyPressed(e -> myController.handleKeyInput(e.getCode()));
    createCamera();
    return scene;
  }

/**
* Generates the new frame of the level
 * @param nextNodes is the list of nodes that the level needs to display for the frame
*/
  public void step(NodeContainer nextNodes){
//    LOG.debug("Camera X: " + myGameCamera.getCameraX());
//    LOG.debug("Camera Y: " + myGameCamera.getCameraX());

    if(myNodes != null){
      for (Node a : myNodes) {
          levelPane.getChildren().remove(a);
      }
    }
    myNodes = nextNodes;
    mainCharacter = myNodes.getMainCharacter();
    updateCamera(mainCharacter);
    translateNodes();
    for (Node node : myNodes) {
        levelPane.getChildren().add(node);
    }
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
    LOG.info(backgroundFile);
    Image backgroundImage = new Image(BACKGROUND_DIRECTORY + backgroundFile);

    newBackground = new Background(new BackgroundImage(backgroundImage, null, null, null, null));

    levelPane.setBackground(newBackground);
  }

}
