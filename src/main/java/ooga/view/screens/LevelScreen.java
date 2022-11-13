package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntity;
import ooga.model.entities.containers.EntityContainer;

public class LevelScreen {



  private final int levelWidth = 1600;

  private final int levelHeight = 800;
  private Pane levelPane;

  private EntityContainer myEntities;
  private String gameType;

  public LevelScreen(EntityContainer initialEntities){
    myEntities = initialEntities;
  }

/**
* Creates a level based on only it's LevelName. Currently, this method is for testing a level by just calling it and creating its initial entities
 * @param levelName
*/
  public LevelScreen(String levelName){

  }

  public Scene initiateLevel(){
    levelPane = new Pane();
    createEntities();

    levelPane.setId("Pane");



    Scene scene = new Scene(levelPane, levelWidth, levelHeight);
    return scene;
  }

  public void step(EntityContainer nextEntities){
    myEntities = nextEntities;
    createEntities();
  }

  private void createEntities(){
    for(int i = 0; i < myEntities.getContainerSize(); i++){
      ImmutableEntity newEntity = myEntities.getEntity(i);
      createMainCharacter(newEntity);
    }
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
