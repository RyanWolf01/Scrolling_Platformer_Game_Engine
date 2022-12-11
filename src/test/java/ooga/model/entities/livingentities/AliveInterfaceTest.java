package ooga.model.entities.livingentities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ResourceBundle;
import ooga.model.entities.entitymodels.BasicStaticCharacter;
import ooga.model.entities.entitymodels.StaticCharacter;
import ooga.model.entities.info.EntityInfo;
import org.junit.jupiter.api.Test;

public class AliveInterfaceTest {

  @Test
  void testSetInitialLives1(){
    EntityInfo entityInfo = new EntityInfo("example");
    entityInfo.set("lives", "3");

    StaticCharacter character = new BasicStaticCharacter( null,0, 0, 2, 2,entityInfo);

    assertEquals(3, character.getLives());
  }

  @Test
  void testSetInitialLives2(){
    ResourceBundle defaultAttributesProperties = ResourceBundle.getBundle("properties/defaultAttributes");
    EntityInfo entityInfo = new EntityInfo("example");
    entityInfo.set("lives", "wrong input");

    StaticCharacter character = new BasicStaticCharacter( null,0, 0, 2, 2,entityInfo);

    int defaultLives = Integer.parseInt(defaultAttributesProperties.getString("lives"));
    assertEquals(defaultLives, character.getLives());
  }

  @Test
  void testSetInitialLives3(){
    ResourceBundle defaultAttributesProperties = ResourceBundle.getBundle("properties/defaultAttributes");
    EntityInfo entityInfo = new EntityInfo("example");

    StaticCharacter character = new BasicStaticCharacter( null,0, 0, 2, 2,entityInfo);

    int defaultLives = Integer.parseInt(defaultAttributesProperties.getString("lives"));
    assertEquals(defaultLives, character.getLives());
  }

}
