package ooga.model.collisions.physics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import ooga.model.entities.ImmutableEntity;

/**
 * Wrapper class that encapsulates a hashmap of <ImmutableEntity, CollisionPhysicsInfo>
 */
public class CurrentCollisionContainer implements Iterable<ImmutableEntity> {
  private final Map<ImmutableEntity, CollisionPhysicsInfo> myCurrentCollisions;

  public CurrentCollisionContainer() {
    myCurrentCollisions = new HashMap<>();
  }

  public CollisionPhysicsInfo get(ImmutableEntity entity) {
    return myCurrentCollisions.get(entity);
  }

  public CollisionPhysicsInfo set(ImmutableEntity entity, CollisionPhysicsInfo collisionPhysicsInfo) {
    return myCurrentCollisions.put(entity, collisionPhysicsInfo);
  }

  public boolean containsKey(ImmutableEntity entity) {
    return myCurrentCollisions.containsKey(entity);
  }

  public void remove(ImmutableEntity entity) {
    myCurrentCollisions.remove(entity);
  }

  @Override
  public Iterator<ImmutableEntity> iterator() {
    return myCurrentCollisions.keySet().iterator();
  }

}
