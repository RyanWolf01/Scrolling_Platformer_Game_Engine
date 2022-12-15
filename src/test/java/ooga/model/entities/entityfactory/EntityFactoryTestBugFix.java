package ooga.model.entities.entityfactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ResourceBundle;
import ooga.controller.JSONInformationDecoder;
import ooga.model.entities.EntityFactory;
import ooga.model.entities.entitymodels.MovingCharacter;
import ooga.model.entities.info.EntityInfo;
import org.junit.jupiter.api.Test;

public class EntityFactoryTestBugFix {
  public JSONInformationDecoder decoder;
  EntityFactory factory;

  @Test
  public void basicAutoMoverTest(){
    decoder = new JSONInformationDecoder("src/test/resources/entityfactorybugtest/level1/level.json",
        "src/test/resources/entityfactorybugtest/level1/collisions.json",
        "src/test/resources/entityfactorybugtest/level1/controls.json");


    factory = new EntityFactory(decoder);
    EntityInfo info = new EntityInfo("doodle_jumper");
    info.set("texture", "/url");
    info.set("lives","1");
    info.set("collidable_type", "jumper");

    MovingCharacter character =  factory.makeMainCharacter(0,0,0,0,"doodle_jumper", info);

    assertEquals(0, character.getXCoordinate());
    assertEquals(0, character.getYCoordinate());
    assertEquals(1, character.getLives());
  }
}
