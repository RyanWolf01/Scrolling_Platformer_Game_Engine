package ooga.model.collisionhandling;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Action;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.collisions.collisionhandling.CollisionData;
import ooga.model.collisions.collisionhandling.Criteria;
import ooga.model.collisions.physics.CollisionDirection;
import ooga.model.collisions.physics.CollisionPhysicsData;
import ooga.model.entities.info.Info;
import org.junit.jupiter.api.Test;

public class CriteriaTest {

  @Test
  public void testCriteriaMatch() {
    // TODO: Change Criteria to not take Map<String, String>

    // Make criteria data
    Map<String, String> map = getMapForCriteria();

    // Make ActionDataContainer data
    ActionDataContainer adc = getActionDataContainer();

    // Create Criteria object
    Criteria criteria = new Criteria(map, adc);

    // Create Info objects and CollisionPhysicsData for CollisionData
    Info infoA = getInfoA();
    Info infoB = getInfoB();

    CollisionPhysicsData cpd = new CollisionPhysicsData(true, 1, CollisionDirection.BOTTOM);
    CollisionData collisionData = new CollisionData(infoA, infoB, cpd);

    // Make sure the criteria matches
    assertTrue(criteria.matches(collisionData));

  }

  @Test
  public void testCriteriaDoNotMatch_becauseOfCriteriaKeyNotInCollisionData() {

    // Make criteria data
    Map<String, String> map = getMapForCriteria();
    map.remove("my_Test");
    map.put("my_Testy", "123");

    // Make ActionDataContainer data
    ActionDataContainer adc = getActionDataContainer();

    // Create Criteria object
    Criteria criteria = new Criteria(map, adc);

    // Create Info objects and CollisionPhysicsData for CollisionData
    Info infoA = getInfoA();
    Info infoB = getInfoB();

    CollisionPhysicsData cpd = new CollisionPhysicsData(true, 1, CollisionDirection.BOTTOM);
    CollisionData collisionData = new CollisionData(infoA, infoB, cpd);

    // Make sure the criteria matches
    assertFalse(criteria.matches(collisionData));

  }

  @Test
  public void testCriteriaDoNotMatch_becauseOfNumConsecutiveCollisionsGreaterThanOne() {

    // Make criteria data
    Map<String, String> map = getMapForCriteria();

    // Make ActionDataContainer data
    ActionDataContainer adc = getActionDataContainer();

    // Create Criteria object
    Criteria criteria = new Criteria(map, adc);

    // Create Info objects and CollisionPhysicsData for CollisionData
    Info infoA = getInfoA();
    Info infoB = getInfoB();

    CollisionPhysicsData cpd = new CollisionPhysicsData(true, 2, CollisionDirection.BOTTOM);
    CollisionData collisionData = new CollisionData(infoA, infoB, cpd);

    // Make sure the criteria matches
    assertFalse(criteria.matches(collisionData));

  }

  @Test
  public void testCriteriaDoNotMatch_becauseOfCriteriaValueDoesNotMatchCollisionData() {

    // Make criteria data
    Map<String, String> map = getMapForCriteria();
    map.put("my_Test", "1234");

    // Make ActionDataContainer data
    ActionDataContainer adc = getActionDataContainer();

    // Create Criteria object
    Criteria criteria = new Criteria(map, adc);

    // Create Info objects and CollisionPhysicsData for CollisionData
    Info infoA = getInfoA();
    Info infoB = getInfoB();

    CollisionPhysicsData cpd = new CollisionPhysicsData(true, 1, CollisionDirection.BOTTOM);
    CollisionData collisionData = new CollisionData(infoA, infoB, cpd);

    // Make sure the criteria matches
    assertFalse(criteria.matches(collisionData));

  }

  @Test
  public void testCriteriaMatch_withUsingStar() {

    // Make criteria data
    Map<String, String> map = getMapForCriteria();
    map.remove("my_Test");
    map.put("my_Test", "*");

    // Make ActionDataContainer data
    ActionDataContainer adc = getActionDataContainer();

    // Create Criteria object
    Criteria criteria = new Criteria(map, adc);

    // Create Info objects and CollisionPhysicsData for CollisionData
    Info infoA = getInfoA();
    Info infoB = getInfoB();

    CollisionPhysicsData cpd = new CollisionPhysicsData(true, 1, CollisionDirection.BOTTOM);
    CollisionData collisionData = new CollisionData(infoA, infoB, cpd);

    // Make sure the criteria matches
    assertTrue(criteria.matches(collisionData));

  }

  @Test
  public void testCriteriaMatch_withUsingOr() {

    // Make criteria data
    Map<String, String> map = getMapForCriteria();
    map.remove("my_Test");
    map.put("my_Test", "yeet||123");

    // Make ActionDataContainer data
    ActionDataContainer adc = getActionDataContainer();

    // Create Criteria object
    Criteria criteria = new Criteria(map, adc);

    // Create Info objects and CollisionPhysicsData for CollisionData
    Info infoA = getInfoA();
    Info infoB = getInfoB();

    CollisionPhysicsData cpd = new CollisionPhysicsData(true, 1, CollisionDirection.BOTTOM);
    CollisionData collisionData = new CollisionData(infoA, infoB, cpd);

    // Make sure the criteria matches
    assertTrue(criteria.matches(collisionData));

  }

  @Test
  public void testCriteriaObjectReturnsActionDataContainer_uponMatch() {
    // relies on the first test working properly

    // Make criteria data
    Map<String, String> map = getMapForCriteria();

    // Make ActionDataContainer data
    ActionDataContainer adc = getActionDataContainer();

    // Create Criteria object
    Criteria criteria = new Criteria(map, adc);

    // Create Info objects and CollisionPhysicsData for CollisionData
    Info infoA = getInfoA();
    Info infoB = getInfoB();

    CollisionPhysicsData cpd = new CollisionPhysicsData(true, 1, CollisionDirection.BOTTOM);
    CollisionData collisionData = new CollisionData(infoA, infoB, cpd);

    // Make sure the criteria matches
    assertSame(adc, criteria.getActionDatas(collisionData));

  }

  @Test
  public void testCriteriaObjectThrowsError_whenGettingActionDataContainer_whenNoMatch() {
    // relies on the first test working properly

    // Make criteria data
    Map<String, String> map = getMapForCriteria();
    map.put("fkldsslfkadsk", "2300w0fs0");

    // Make ActionDataContainer data
    ActionDataContainer adc = getActionDataContainer();

    // Create Criteria object
    Criteria criteria = new Criteria(map, adc);

    // Create Info objects and CollisionPhysicsData for CollisionData
    Info infoA = getInfoA();
    Info infoB = getInfoB();

    CollisionPhysicsData cpd = new CollisionPhysicsData(true, 1, CollisionDirection.BOTTOM);
    CollisionData collisionData = new CollisionData(infoA, infoB, cpd);

    // Make sure the criteria matches
    assertThrows(RuntimeException.class, () -> criteria.getActionDatas(collisionData));

  }

  private Map<String, String> getMapForCriteria() {
    // Make criteria data
    Map<String, String> map = new HashMap<>();
    map.put("my_Test", "123");
    map.put("opponent_FOO", "bar");
    map.put("collision_direction", "bottom");

    return map;
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
