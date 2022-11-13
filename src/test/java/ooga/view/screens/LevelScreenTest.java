package ooga.view.screens;

import javafx.stage.Stage;
import ooga.model.entities.Entity;
import ooga.model.entities.characters.Mario;
import ooga.model.entities.containers.EntityContainer;
import ooga.model.entities.data.InitialAttributes;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import javax.swing.text.View;

public class LevelScreenTest extends DukeApplicationTest {

  private LevelScreen myLevel;
  private EntityContainer testEntities;




  @Override
  public void start (Stage stage){
    EntityContainer testEntities = new EntityContainer();
    Entity testMario = new Mario(new InitialAttributes(0, 0), null, 2, 2, 0);
    testEntities.addEntity(testMario);
    myLevel = new LevelScreen(testEntities);
    stage.setScene(myLevel.initiateLevel());
    stage.show();
  }




  @Test
  void testMarioLocation(){
    EntityContainer testEntities = new EntityContainer();
    Entity testMario = new Mario(new InitialAttributes(3, 3), null, 2, 2, 0);
    testEntities.addEntity(testMario);
    myLevel.step(testEntities);
  }

}
