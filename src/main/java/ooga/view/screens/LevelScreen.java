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



  private final int levelWidth = 1600;

  private final int levelHeight = 800;
  private final String BACKGROUND_DIRECTORY = Main.DEFAULT_RESOURCE_PACKAGE + "backgrounds\\";
  private Pane levelPane;

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

  public void step(NodeContainer nextNodes){
    myNodes = nextNodes;
    //get MainCharacter from myNodes
    ScrollingNode mainCharacter = myNodes.getMainCharacter();
    updateCamera(mainCharacter);
    translateNodes();
    for (Node node : myNodes) {
        levelPane.getChildren().add(node);
    }
  }


  private void createCamera(){
    double startX = 0;
    double startY = 0;
    Margin startingMargin = new Margin(100, 800, 0, 0);
    int height = levelHeight;
    int width = levelWidth;
    myGameCamera = new GameCamera(startX, startY, startingMargin, height, width);
    myGameCamera.setPlayerLocation(0, 0);
  }
  private void updateCamera(ScrollingNode mainCharacter){
    //get player location from myNodes
    double playerX = mainCharacter.getBackX();
    double playerY = mainCharacter.getBackY();
    myGameCamera.setPlayerLocation(playerX, playerY);
  }

  private void translateNodes(){
    myNodes.updateCameraPosition(myGameCamera.getCameraX(), myGameCamera.getCameraY());
  }

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
