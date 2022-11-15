package ooga.view.screens;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ooga.controller.GameController;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntity;
import ooga.model.entities.containers.EntityContainer;
import ooga.view.nodes.NodeContainer;
import ooga.view.nodes.ScrollingNode;

import java.util.Iterator;

public class LevelScreen {



  private final int levelWidth = 1600;

  private final int levelHeight = 800;
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

  public Scene initiateLevel(){
    levelPane = new Pane();

    levelPane.setId("Pane");



    Scene scene = new Scene(levelPane, levelWidth, levelHeight);
    return scene;
  }

  public void step(double frameTime){
    NodeContainer nextNodes = myController.step();
    myNodes = nextNodes;
    createEntities();
  }

  private void createEntities(){
    Iterator<Node> nodeIterator = myNodes.iterator();
    while(nodeIterator.hasNext()) {
      Node element = nodeIterator.next();
      createEntity(element);
      System.out.print(element + " ");
    }
  }

  //TODO: Actually Create the Elements in NodeContainer
  private void createEntity(Node newElement){
    levelPane.getChildren().add(newElement);
  }
  private void createImageOnScreen(ImmutableEntity newEntity){

  }

  private void createMainCharacter(ImmutableEntity newEntity){
    //ImageView newImage = new ImageView(System.getProperty("user.dir") + "/data" + "/games" + "/" + gameType);
    ImageView newImage = new ImageView(System.getProperty("user.dir") + "/data/games/mario/assets/mario.png");
    newImage.setY(newEntity.getYCoordinate());
    newImage.setX(newEntity.getXCoordinate());
    levelPane.getChildren().add(newImage);
  }

}
