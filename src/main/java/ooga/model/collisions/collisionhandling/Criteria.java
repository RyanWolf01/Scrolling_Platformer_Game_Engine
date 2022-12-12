package ooga.model.collisions.collisionhandling;

import ooga.model.collisions.actiondata.ActionDataContainer;

public interface Criteria {
  boolean matches(CollisionData collisionData);
  ActionDataContainer getActionDatas(CollisionData collisionData);

}
