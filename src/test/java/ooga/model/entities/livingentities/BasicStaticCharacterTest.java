package ooga.model.entities.livingentities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.entitymodels.BasicStaticCharacter;
import ooga.model.entities.entitymodels.StaticCharacter;
import ooga.model.entities.info.EntityInfo;
import org.junit.jupiter.api.Test;

public class BasicStaticCharacterTest {
  @Test
  void testIncrementLivesPositive1() {
    EntityInfo entityInfo = new EntityInfo("example");

    StaticCharacter character = new BasicStaticCharacter( null,0, 0, 2, 2,entityInfo);

    character.changeLives(1);
    assertEquals(2, character.getLives());
  }

  @Test
  void testIncrementLivesPositive2() {
    BasicStaticCharacter character = new BasicStaticCharacter(null,0, 0, 2, 2, new EntityInfo("example"));

    character.changeLives(100);
    assertEquals(101, character.getLives());
  }

  @Test
  void testIncrementLivesNegative() {
    BasicStaticCharacter character = new BasicStaticCharacter(null,0, 0, 2, 2, new EntityInfo("example"));

    character.changeLives(0);
    assertEquals(1, character.getLives());
  }

  @Test
  void testDecrementLivesPositive1() {
    BasicStaticCharacter character = new BasicStaticCharacter(null,0, 0, 2, 2, new EntityInfo("example"));

    character.changeLives(1);
    assertEquals(2, character.getLives());
  }

  @Test
  void testDecrementLivesPositive2() {
    BasicStaticCharacter character = new BasicStaticCharacter(null,0, 0, 2, 2, new EntityInfo("example"));

    character.changeLives(101);
    assertEquals(102, character.getLives());
  }

  @Test
  void testDecrementLivesNegative() {
    BasicStaticCharacter character = new BasicStaticCharacter(null,0, 0, 2, 2, new EntityInfo("example"));

    character.changeLives(0);
    assertEquals(1, character.getLives());
  }

  @Test
  void testKill() {
    StaticCharacter character = new BasicStaticCharacter( null,0, 0, 2, 2, new EntityInfo("example"));
    character.changeLives(2);
    character.kill();
    assertEquals(2, character.getLives());
  }

  @Test
  void testPerformActionsPos1(){
    BasicStaticCharacter character = new BasicStaticCharacter(null,0, 0, 2, 2, new EntityInfo("example"));

    List<ActionData> actionList = new ArrayList<>();
    List<String> params = new ArrayList<>();
    ActionData data = new ActionData("ooga.model.actions.aliveactions.IncreaseLife", "AliveAction", params);
    actionList.add(data);
    ActionDataContainer container = new ActionDataContainer(actionList);
//    character.performActions(container);

    assertEquals(1, character.getLives());
  }

  @Test
  void testPerformActionsPos2(){
    BasicStaticCharacter character = new BasicStaticCharacter(null,0, 0, 2, 2, new EntityInfo("example"));
    character.changeLives(2);
    List<ActionData> actionList = new ArrayList<>();
    List<String> params = new ArrayList<>();
    ActionData data = new ActionData("ooga.model.actions.aliveactions.Kill", "AliveAction", params);
    actionList.add(data);
    ActionDataContainer container = new ActionDataContainer(actionList);
//    character.performActions(container);

    assertEquals(1, character.getLives());
  }

}
