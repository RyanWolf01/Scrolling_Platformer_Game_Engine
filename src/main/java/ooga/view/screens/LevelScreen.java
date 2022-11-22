package ooga.view.screens;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ooga.Main;
import ooga.controller.GameController;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntity;
import ooga.model.entities.containers.EntityContainer;
import ooga.view.nodes.NodeContainer;
import ooga.view.nodes.ScrollingNode;
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
    return scene;
  }

  public void step(NodeContainer nextNodes){
    myNodes = nextNodes;
  }


  private void updateCamera(){

  }

  private void setBackground(File levelFile) throws IOException, ParseException {
    Background newBackground;
    FileReader infoFile = new FileReader(levelFile);
    JSONObject levelJson = (JSONObject) new JSONParser().parse(infoFile);
    String backgroundFile = (String) levelJson.get("background");
    System.out.println(backgroundFile);
    Image backgroundImage = new Image(BACKGROUND_DIRECTORY + backgroundFile);

    newBackground = new Background(new BackgroundImage(backgroundImage, null, null, null, null));

    levelPane.setBackground(newBackground);
  }

}
