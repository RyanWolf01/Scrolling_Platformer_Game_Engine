package ooga.model.actionparsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.characters.maincharacters.Mario;
import ooga.model.entities.data.EntityInfo;
import ooga.model.entities.movement.Mover;
import org.junit.jupiter.api.Test;

public class MoverActionParserTest {

  public static final String ALIVE_ACTION_PATH = "ooga.model.actions.aliveactions.";
  public static final String MOVER_ACTION_PATH = "ooga.model.actions.moveractions.";
  public static final String ALIVE_ACTION = "AliveAction";
  public static final String MOVER_ACTION = "MoverAction";

  @Test
  void test_parseAndApplyActions() {

    ActionDataContainer acd = new ActionDataContainer();
    addActionData(acd, "Bounce");
    addActionData(acd, "basicmovement.LeftMovement");
    addActionData(acd, "StopXMovement");
    MoverActionParser moverActionParser = new MoverActionParser(acd);

    Mover mover = new Mario(0, 0, 10, 10, new EntityInfo("MARIO"));

    int res = moverActionParser.parseAndApplyActions(mover);
    assertEquals(3, res);
  }

  @Test
  void test_parseAndApplyActions_badClassName() {
    ActionDataContainer acd = new ActionDataContainer();
    addActionData(acd, "yeet");
    MoverActionParser actionParser = new MoverActionParser(acd);
    Mover mover = new Mario(0, 0, 10, 10, new EntityInfo("MARIO"));

    assertThrows(ActionParsingException.class, () -> actionParser.parseAndApplyActions(mover));
  }

  @Test
  void test_parseAndApplyActions_noAliveActions() {
    ActionDataContainer acd = new ActionDataContainer();
    acd.addActionData(
        new ActionData(ALIVE_ACTION_PATH + "IncreaseLife", ALIVE_ACTION, new ArrayList<>()));
    acd.addActionData(
        new ActionData(ALIVE_ACTION_PATH + "Kill", ALIVE_ACTION, new ArrayList<>()));

    Mover mover = new Mario(0, 0, 10, 10, new EntityInfo("MARIO"));
    MoverActionParser moverActionParser = new MoverActionParser(acd);
    int count = moverActionParser.parseAndApplyActions(mover);

    assertEquals(0, count);
  }

  @Test
  void test_parseAndApplyActions_noActions() {
    ActionDataContainer acd = new ActionDataContainer();

    Mover mover = new Mario(0, 0, 10, 10, new EntityInfo("MARIO"));
    MoverActionParser moverActionParser = new MoverActionParser(acd);
    int count = moverActionParser.parseAndApplyActions(mover);

    assertEquals(0, count);
  }


  private void addActionData(ActionDataContainer acd, String className) {
    acd.addActionData(
        new ActionData(MOVER_ACTION_PATH + className, MOVER_ACTION, new ArrayList<>()));
  }

}
