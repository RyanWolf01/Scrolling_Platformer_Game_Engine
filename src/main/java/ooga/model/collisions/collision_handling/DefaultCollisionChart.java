package ooga.model.collisions.collision_handling;

import java.util.Collection;
import java.util.Iterator;
import ooga.model.actions.Action;
import ooga.model.collisions.CollisionData;
import ooga.model.entities.ImmutableEntityInfo;

public class DefaultCollisionChart implements CollisionChart {
  public Action getAction(ImmutableEntityInfo targetEntityInfo, ImmutableEntityInfo sourceEntityInfo, CollisionData collisionData) {
    return null;
  }
}
