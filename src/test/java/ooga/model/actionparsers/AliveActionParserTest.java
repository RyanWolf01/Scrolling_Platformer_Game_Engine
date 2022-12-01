package ooga.model.actionparsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.characters.maincharacters.Mario;
import ooga.model.entities.data.EntityInfo;
import org.junit.jupiter.api.Test;

public class AliveActionParserTest {

  public static final String ALIVE_ACTION_PATH = "ooga.model.actions.aliveactions.";
  public static final String MOVER_ACTION_PATH = "ooga.model.actions.moveractions.";
  public static final String ALIVE_ACTION = "AliveAction";
  public static final String MOVER_ACTION = "MoverAction";

//
//  @Test
//  void test_parseAndApplyActions() {
//
//    ActionDataContainer acd = new ActionDataContainer();
//    addActionData(acd, "IncreaseLife");
//    addActionData(acd, "IncreaseLife");
//    addActionData(acd, "Kill");
//    AliveActionParser aliveActionParser = new AliveActionParser(acd);
//
//    Alive alive = new Mario(0, 0, 10, 10, new EntityInfo("MARIO"));
//    int numLives = alive.getLives();
//
//    int res = aliveActionParser.parseAndApplyActions(alive);
//    assertEquals(3, res);
//
//    // Assertion below tests functionality of IncreaseLife and Kill Actions
//    assertEquals(numLives + 1, alive.getLives());
//  }
//
//  @Test
//  void test_parseAndApplyActions_badClassName() {
//    ActionDataContainer acd = new ActionDataContainer();
//    addActionData(acd, "IncreaseLifeee");
//    AliveActionParser actionParser = new AliveActionParser(acd);
//    Alive alive = new Mario(0, 0, 10, 10, new EntityInfo("MARIO"));
//
//    assertThrows(ActionParsingException.class, () -> actionParser.parseAndApplyActions(alive));
//  }
//
//  @Test
//  void test_parseAndApplyActions_noAliveActions() {
//    ActionDataContainer acd = new ActionDataContainer();
//    acd.addActionData(
//        new ActionData(MOVER_ACTION_PATH + "Bounce", MOVER_ACTION, new ArrayList<>()));
//    acd.addActionData(
//        new ActionData(MOVER_ACTION_PATH + "StopXMovement", MOVER_ACTION, new ArrayList<>()));
//
//    Alive alive = new Mario(0, 0, 10, 10, new EntityInfo("MARIO"));
//    AliveActionParser aliveActionParser = new AliveActionParser(acd);
//    int count = aliveActionParser.parseAndApplyActions(alive);
//
//    assertEquals(0, count);
//  }
//
//  @Test
//  void test_parseAndApplyActions_noActions() {
//    ActionDataContainer acd = new ActionDataContainer();
//
//    Alive alive = new Mario(0, 0, 10, 10, new EntityInfo("MARIO"));
//    AliveActionParser aliveActionParser = new AliveActionParser(acd);
//    int count = aliveActionParser.parseAndApplyActions(alive);
//
//    assertEquals(0, count);
//  }
//
//  private void addActionData(ActionDataContainer acd, String className) {
//    acd.addActionData(
//        new ActionData(ALIVE_ACTION_PATH + className, ALIVE_ACTION, new ArrayList<>()));
//  }
}
