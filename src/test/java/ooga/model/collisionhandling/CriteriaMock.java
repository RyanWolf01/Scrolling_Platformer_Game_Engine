package ooga.model.collisionhandling;

import javax.accessibility.Accessible;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.collisions.collisionhandling.CollisionData;
import ooga.model.collisions.collisionhandling.Criteria;

public class CriteriaMock implements Criteria {
  CollisionData collisionDataObjectToMatch;
  ActionDataContainer actionDataContainer;

  public CriteriaMock(CollisionData collisionDataObjectToMatch, ActionDataContainer actionDataContainer) {
    this.collisionDataObjectToMatch = collisionDataObjectToMatch;
    this.actionDataContainer = actionDataContainer;
  }

  @Override
  public boolean matches(CollisionData collisionData) {
    return collisionDataObjectToMatch == collisionData;
  }

  @Override
  public ActionDataContainer getActionDatas(CollisionData collisionData) {
    if (matches(collisionData)) {
      return actionDataContainer;
    }
    else {
      throw new RuntimeException("This CollisionData doesn't match!");
    }
  }
}
