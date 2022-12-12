package ooga.model.collisionhandling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.collisions.collisionhandling.CollisionData;
import ooga.model.collisions.collisionhandling.Criteria;
import ooga.model.collisions.collisionhandling.DefaultCollisionChart;
import ooga.model.collisions.collisionhandling.exceptions.NoCollisionCriteriaMatchException;
import ooga.model.collisions.physics.CollisionDirection;
import ooga.model.collisions.physics.CollisionPhysicsData;
import ooga.model.entities.info.Info;
import org.junit.jupiter.api.Test;

public class DefaultCollisionChartTest {

  @Test
  public void testDefaultCollisionChart() {
    CollisionChart cc = new DefaultCollisionChart();

    // Generate criteria1 that matches with collisionData1
    CollisionData collisionData1 = new CollisionData(new Info(), new Info(),
        new CollisionPhysicsData(true, 1, CollisionDirection.RIGHT));
    ActionDataContainer actionDataContainer1 = new ActionDataContainer();
    Criteria criteria1 = getCriteria(collisionData1, actionDataContainer1);
    cc.addCriteria(criteria1);

    // Generate criteria2 that matches with collisionData2
    CollisionData collisionData2 = new CollisionData(new Info(), new Info(),
        new CollisionPhysicsData(true, 1, CollisionDirection.RIGHT));
    ActionDataContainer actionDataContainer2 = new ActionDataContainer();
    Criteria criteria2 = getCriteria(collisionData2, actionDataContainer2);
    cc.addCriteria(criteria2);

    // Generate criteria3 that matches with collisionData3
    CollisionData collisionData3 = new CollisionData(new Info(), new Info(),
        new CollisionPhysicsData(true, 1, CollisionDirection.RIGHT));
    ActionDataContainer actionDataContainer3 = new ActionDataContainer();
    Criteria criteria3 = getCriteria(collisionData3, actionDataContainer3);
    cc.addCriteria(criteria3);

    ActionDataContainer matchedADC = cc.getActionDatas(collisionData2);
    assertEquals(actionDataContainer2, matchedADC);
    assertNotEquals(actionDataContainer1, matchedADC);
    assertNotEquals(actionDataContainer3, matchedADC);

  }

  @Test
  public void testDefaultCollisionChart_throwsExceptionWhenNothingMatched() {
    CollisionChart cc = new DefaultCollisionChart();

    // Generate criteria1 that matches with collisionData1
    CollisionData collisionData1 = new CollisionData(new Info(), new Info(),
        new CollisionPhysicsData(true, 1, CollisionDirection.RIGHT));
    ActionDataContainer actionDataContainer1 = new ActionDataContainer();
    Criteria criteria1 = getCriteria(collisionData1, actionDataContainer1);
    cc.addCriteria(criteria1);

    CollisionData collisionData2 = new CollisionData(new Info(), new Info(),
        new CollisionPhysicsData(true, 1, CollisionDirection.RIGHT));

    assertThrows(NoCollisionCriteriaMatchException.class, () -> cc.getActionDatas(collisionData2));

  }

  @Test
  public void testDefaultCollisionChart_throwsExceptionWhenNothingLoaded() {
    CollisionChart cc = new DefaultCollisionChart();
    CollisionData collisionData = new CollisionData(new Info(), new Info(), new CollisionPhysicsData(true, 1, CollisionDirection.BOTTOM));
    assertThrows(NoCollisionCriteriaMatchException.class, () -> cc.getActionDatas(collisionData));
  }

  @Test
  public void testAddCriteriaMethod() {
    CollisionChart cc = new DefaultCollisionChart();
    CollisionData collisionData = new CollisionData(new Info(), new Info(), new CollisionPhysicsData(true, 1, CollisionDirection.BOTTOM));
    assertThrows(NoCollisionCriteriaMatchException.class, () -> cc.getActionDatas(collisionData));

    ActionDataContainer res = new ActionDataContainer();
    cc.addCriteria(new CriteriaMock(collisionData, res));
    assertEquals(cc.getActionDatas(collisionData), res);
  }


  private Criteria getCriteria(CollisionData collisionDataToMatch,
      ActionDataContainer actionDataContainer) {
    actionDataContainer.addActionData(new ActionData("1", "1", new ArrayList<>()));
    return new CriteriaMock(collisionDataToMatch, actionDataContainer);
  }
}
