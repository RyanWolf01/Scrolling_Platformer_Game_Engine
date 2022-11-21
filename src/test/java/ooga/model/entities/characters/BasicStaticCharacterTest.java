package ooga.model.entities.characters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import ooga.model.collisions.data.ActionData;
import ooga.model.collisions.data.ActionDataContainer;
import ooga.model.entities.data.EntityInfo;
import org.junit.jupiter.api.Test;

public class BasicStaticCharacterTest {

  @Test
  void testIncrementLivesPositive1() {
    StaticCharacter character = new BasicStaticCharacter( 0, 0, 2, 2, new EntityInfo("example"));

    character.changeLives(1);
    assertEquals(1, character.getLives());
  }

  @Test
  void testIncrementLivesPositive2() {
    BasicStaticCharacter character = new BasicStaticCharacter(0, 0, 2, 2, new EntityInfo("example"));

    character.changeLives(100);
    assertEquals(100, character.getLives());
  }

  @Test
  void testIncrementLivesNegative() {
    BasicStaticCharacter character = new BasicStaticCharacter(0, 0, 2, 2, new EntityInfo("example"));

    character.changeLives(0);
    assertEquals(0, character.getLives());
  }

  @Test
  void testDecrementLivesPositive1() {
    BasicStaticCharacter character = new BasicStaticCharacter(0, 0, 2, 2, new EntityInfo("example"));

    character.changeLives(1);
    assertEquals(1, character.getLives());
  }

  @Test
  void testDecrementLivesPositive2() {
    BasicStaticCharacter character = new BasicStaticCharacter(0, 0, 2, 2, new EntityInfo("example"));

    character.changeLives(101);
    assertEquals(101, character.getLives());
  }

  @Test
  void testDecrementLivesNegative() {
    BasicStaticCharacter character = new BasicStaticCharacter(0, 0, 2, 2, new EntityInfo("example"));

    character.changeLives(0);
    assertEquals(0, character.getLives());
  }

  @Test
  void testKill() {
    StaticCharacter character = new BasicStaticCharacter( 0, 0, 2, 2, new EntityInfo("example"));
    character.setLives(2);
    character.kill();
    assertEquals(1, character.getLives());
  }

  @Test
  void testPerformActionsPos1(){
    BasicStaticCharacter character = new BasicStaticCharacter(0, 0, 2, 2, new EntityInfo("example"));

    List<ActionData> actionList = new ArrayList<>();
    List<String> params = new ArrayList<>();
    ActionData data = new ActionData("ooga.model.actions.aliveactions.IncreaseLife", "AliveAction", params);
    actionList.add(data);
    ActionDataContainer container = new ActionDataContainer(actionList);
    character.performActions(container);

    assertEquals(1, character.getLives());
  }

  @Test
  void testPerformActionsPos2(){
    BasicStaticCharacter character = new BasicStaticCharacter(0, 0, 2, 2, new EntityInfo("example"));
    character.setLives(2);
    List<ActionData> actionList = new ArrayList<>();
    List<String> params = new ArrayList<>();
    ActionData data = new ActionData("ooga.model.actions.aliveactions.Kill", "AliveAction", params);
    actionList.add(data);
    ActionDataContainer container = new ActionDataContainer(actionList);
    character.performActions(container);

    assertEquals(1, character.getLives());
  }

  @Test
  void testPerformActionsNeg(){
    BasicStaticCharacter character = new BasicStaticCharacter(0, 0, 2, 2, new EntityInfo("example"));

    List<ActionData> actionList = new ArrayList<>();
    List<String> params = new ArrayList<>();
    ActionData data = new ActionData("ooga.model.actions.aliveactions.Kill", "AliveAction", params);
    actionList.add(data);
    ActionDataContainer container = new ActionDataContainer(actionList);
    character.performActions(container);

    assertEquals(0, character.getLives());
  }

}
