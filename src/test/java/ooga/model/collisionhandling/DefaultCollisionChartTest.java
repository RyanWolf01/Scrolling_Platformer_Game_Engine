package ooga.model.collisionhandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.collisions.collisionhandling.CriteriaDefault;
import ooga.model.collisions.collisionhandling.DefaultCollisionChart;
import ooga.model.entities.info.Info;
import org.junit.jupiter.api.Test;

public class DefaultCollisionChartTest {

  @Test
  public void testDefaultCollisionChart() {
    CollisionChart cc = new DefaultCollisionChart();

  }

  private CriteriaDefault getCriteria1() {
    // Make criteria data
    Map<String, String> map = new HashMap<>();
    map.put("MY_TYPE", "123");
    map.put("OPPONENT_FOO", "bar");
    map.put("collision_direction", "bottom");

    // Make ActionDataContainer data
    ActionDataContainer adc = getActionDataContainer();

    // Create Criteria object
    return new CriteriaDefault(map, adc);

  }

  private CriteriaDefault getCriteria2() {
    // Make criteria data
    Map<String, String> map = new HashMap<>();
    map.put("my_Test", "123");
    map.put("opponent_FOO", "bar");
    map.put("collision_direction", "bottom");

    // Make ActionDataContainer data
    ActionDataContainer adc = getActionDataContainer();

    // Create Criteria object
    return new CriteriaDefault(map, adc);

  }

  private ActionDataContainer getActionDataContainer() {
    ActionDataContainer adc = new ActionDataContainer();
    adc.addActionData(new ActionData("ooga.model.actions.moveractions.basicmovement.RightMovement", "MoverAction", new ArrayList<>()));
    return adc;
  }

  private Info getInfoA() {
    Info infoA = new Info();
    infoA.set("tesT", "123");
    infoA.set("alsdfj", "2392");
    return infoA;
  }

  private Info getInfoB() {
    Info infoB = new Info();
    infoB.set("foo", "BAR");
    infoB.set("dafskl", "492301");
    return infoB;
  }
}
