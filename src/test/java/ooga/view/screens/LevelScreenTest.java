package ooga.view.screens;

import javafx.stage.Stage;
import ooga.Main;
import ooga.model.entities.data.EntityInfo;
import ooga.model.entities.characters.maincharacters.Mario;
import ooga.model.entities.containers.EntityContainer;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.io.File;

public class LevelScreenTest extends DukeApplicationTest {

  private LevelScreen myLevel;
  private EntityContainer testEntities;




  @Override
  public void start (Stage stage){
    testEntities = new EntityContainer();
    Mario testMario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));
    testEntities.addEntity(testMario);
    stage.setScene(myLevel.initiateLevel(new File(Main.DEFAULT_RESOURCE_PACKAGE + "games\\mario\\levels\\testLevel1.json")));
    stage.show();
  }




  @Test
  void testMarioLocation(){
    testEntities = new EntityContainer();
    Mario testMario = new Mario(3, 3, 2, 2, new EntityInfo("MARIO"));
    testEntities.addEntity(testMario);
  }

}
