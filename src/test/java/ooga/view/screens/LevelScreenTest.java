package ooga.view.screens;

import javafx.stage.Stage;
import ooga.model.entities.EntityInfo;
import ooga.model.entities.characters.maincharacters.Mario;
import ooga.model.entities.containers.EntityContainer;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class LevelScreenTest extends DukeApplicationTest {

  private LevelScreen myLevel;
  private EntityContainer testEntities;




  @Override
  public void start (Stage stage){
    EntityContainer testEntities = new EntityContainer();
    Mario testMario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));
    testEntities.addEntity(testMario);
    myLevel = new LevelScreen(testEntities);
    stage.setScene(myLevel.initiateLevel());
    stage.show();
  }




  @Test
  void testMarioLocation(){
    EntityContainer testEntities = new EntityContainer();
    Mario testMario = new Mario(3, 3, 2, 2, new EntityInfo("MARIO"));
    testEntities.addEntity(testMario);
    myLevel.step(testEntities);
  }

}
