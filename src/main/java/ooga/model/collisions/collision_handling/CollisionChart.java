package ooga.model.collisions.collision_handling;

import ooga.model.actions.Action;
import ooga.model.collisions.CollisionPhysicsData;
import ooga.model.entities.ImmutableEntityInfo;

public interface CollisionChart {
  Action getAction(CollisionData collisionData);
}
